package com.cleberleao.estacionamento.service;

import com.cleberleao.estacionamento.dto.RequestVeiculoDTO;
import com.cleberleao.estacionamento.dto.ResponseVeiculoDTO;
import com.cleberleao.estacionamento.dto.SaidaVeiculoDTO;
import com.cleberleao.estacionamento.entity.TipoVeiculo;
import com.cleberleao.estacionamento.entity.Usuario;
import com.cleberleao.estacionamento.entity.Vaga;
import com.cleberleao.estacionamento.entity.Veiculo;
import com.cleberleao.estacionamento.repository.VeiculoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class VeiculoService {

    @Autowired
    private VeiculoRepository veiculoRepository;

    @Autowired
    private VagaService vagaService;

    @Autowired
    private UsuarioService usuarioService;

    private static final double TARIFA_PEQUENO = 16.0;
    private static final double TARIFA_GRANDE = 25.0;
    private static final double TARIFA_MOTO = 8.0;

    public ResponseVeiculoDTO cadastrar(RequestVeiculoDTO dto) {
        // placa única
        if (veiculoRepository.existsByPlaca(dto.getPlaca())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Veículo com placa já cadastrado: " + dto.getPlaca());
        }

        Vaga vaga = vagaService.findByNumero(dto.getVagaNumero())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Vaga não encontrada: " + dto.getVagaNumero()));

        if (vaga.isOcupada()) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Vaga já ocupada: " + dto.getVagaNumero());
        }

        Veiculo veiculo = new Veiculo(dto);

        if (dto.getUsuarioId() != null) {
            Usuario usuario = usuarioService.buscarPorId(dto.getUsuarioId());
            if (usuario == null) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário não encontrado: " + dto.getUsuarioId());
            }
            veiculo.setUsuario(usuario);
        }

        // associar vaga
        vaga.setOcupada(true);
        veiculo.setVaga(vaga);

        // persistir veiculo primeiro para gerar id
        Veiculo salvo = veiculoRepository.save(veiculo);

        // atualizar vaga para referenciar veiculo
        vaga.setVeiculo(salvo);
        vagaService.salvar(vaga);

        return new ResponseVeiculoDTO(salvo);
    }

    public SaidaVeiculoDTO saidaPorPlaca(String placa) {
        Optional<Veiculo> op = veiculoRepository.findByPlaca(placa);
        if (op.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Veículo não encontrado para a placa: " + placa);
        }

        Veiculo v = op.get();
        LocalDateTime entrada = v.getHoraEntrada();
        LocalDateTime agora = LocalDateTime.now();

        long minutos = Duration.between(entrada, agora).toMinutes();
        long horas = (minutos + 59) / 60; // ceil para horas

        double tarifa = tarifaPorTipo(v.getTipo());
        double total = tarifa * horas;

        // liberar vaga
        Vaga vaga = v.getVaga();
        if (vaga != null) {
            vaga.setOcupada(false);
            vaga.setVeiculo(null);
            vagaService.salvar(vaga);
            v.setVaga(null);
        }

        // remover registro do veículo
        veiculoRepository.delete(v);

        SaidaVeiculoDTO dto = new SaidaVeiculoDTO();
        dto.setPlaca(placa);
        dto.setDuracaoHoras(horas);
        dto.setTarifaPorHora(tarifa);
        dto.setTotalPagar(total);
        return dto;
    }

    private double tarifaPorTipo(TipoVeiculo tipo) {
        if (tipo == null) return TARIFA_PEQUENO;
        return switch (tipo) {
            case PEQUENO -> TARIFA_PEQUENO;
            case GRANDE -> TARIFA_GRANDE;
            case MOTO -> TARIFA_MOTO;
        };
    }

    public long totalVeiculosEstacionados() {
        return veiculoRepository.count();
    }
}

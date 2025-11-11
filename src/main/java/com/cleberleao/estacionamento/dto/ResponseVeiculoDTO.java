package com.cleberleao.estacionamento.dto;

import com.cleberleao.estacionamento.entity.Veiculo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseVeiculoDTO {
    private Long id;
    private String placa;
    private String modelo;
    private String marca;
    private String tipo;
    private LocalDateTime horaEntrada;
    private Integer vagaNumero;
    private Long usuarioId;

    public ResponseVeiculoDTO(Veiculo v) {
        this.id = v.getId();
        this.placa = v.getPlaca();
        this.modelo = v.getModelo();
        this.marca = v.getMarca();
        this.tipo = v.getTipo() != null ? v.getTipo().name() : null;
        this.horaEntrada = v.getHoraEntrada();
        this.vagaNumero = v.getVaga() != null ? v.getVaga().getNumero() : null;
        this.usuarioId = v.getUsuario() != null ? v.getUsuario().getId() : null;
    }
}

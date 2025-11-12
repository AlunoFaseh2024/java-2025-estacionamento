package com.cleberleao.estacionamento.service;

import com.cleberleao.estacionamento.entity.Vaga;
import com.cleberleao.estacionamento.repository.VagaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;
import java.util.ArrayList;


import java.util.List;
import java.util.Optional;

@Service
public class VagaService {

    @Autowired
    private VagaRepository vagaRepository;

    public Optional<Vaga> findByNumero(Integer numero) {
        return vagaRepository.findByNumero(numero);
    }

    public Vaga salvar(Vaga vaga) {
        return vagaRepository.save(vaga);
    }

    public List<Vaga> listarTodas() {
        return vagaRepository.findAll();
    }

    public List<Vaga> listarOcupadas() {
        return vagaRepository.findByOcupadaTrue();
    }

    public List<Vaga> listarLivres() {
        return vagaRepository.findByOcupadaFalse();
    }

    public List<Vaga> criarVagas(int quantidade) {
        if (quantidade <= 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Quantidade deve ser maior que zero");
        }

        int ultimoNumero = vagaRepository.findAll().stream()
                .mapToInt(Vaga::getNumero)
                .max()
                .orElse(0);

        List<Vaga> novas = new ArrayList<>();
        for (int i = 1; i <= quantidade; i++) {
            Vaga vaga = new Vaga();
            vaga.setNumero(ultimoNumero + i);
            vaga.setOcupada(false);
            novas.add(vaga);
        }

        return vagaRepository.saveAll(novas);
    }
}


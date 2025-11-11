package com.cleberleao.estacionamento.service;

import com.cleberleao.estacionamento.entity.Vaga;
import com.cleberleao.estacionamento.repository.VagaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}

package com.cleberleao.estacionamento.controller;

import com.cleberleao.estacionamento.entity.Vaga;
import com.cleberleao.estacionamento.service.VagaService;
import com.cleberleao.estacionamento.service.VeiculoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/vagas")
@CrossOrigin("*")
public class VagaController {

    @Autowired
    private VagaService vagaService;

    @Autowired
    private VeiculoService veiculoService;

    @GetMapping
    public ResponseEntity<List<Vaga>> listarTodas() {
        return ResponseEntity.ok(vagaService.listarTodas());
    }

    @GetMapping("/ocupadas")
    public ResponseEntity<List<Vaga>> listarOcupadas() {
        return ResponseEntity.ok(vagaService.listarOcupadas());
    }

    @GetMapping("/livres")
    public ResponseEntity<List<Vaga>> listarLivres() {
        return ResponseEntity.ok(vagaService.listarLivres());
    }

    @GetMapping("/total")
    public ResponseEntity<Map<String, Long>> total() {
        long total = veiculoService.totalVeiculosEstacionados();
        return ResponseEntity.ok(Map.of("total", total));
    }
}

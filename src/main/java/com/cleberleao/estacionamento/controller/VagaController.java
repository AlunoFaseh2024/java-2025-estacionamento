package com.cleberleao.estacionamento.controller;

import com.cleberleao.estacionamento.entity.Vaga;
import com.cleberleao.estacionamento.service.VagaService;
import com.cleberleao.estacionamento.service.VeiculoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.cleberleao.estacionamento.dto.RequestVagaDTO;
import org.springframework.http.HttpStatus;


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

    @PostMapping
    public ResponseEntity<Map<String, Object>> criarVagas(@RequestBody RequestVagaDTO dto) {
        List<Vaga> criadas = vagaService.criarVagas(dto.getQuantidade());
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(Map.of(
                        "mensagem", criadas.size() + " vagas criadas com sucesso",
                        "totalCriadas", criadas.size()
                ));
    }

}

package com.cleberleao.estacionamento.controller;

import com.cleberleao.estacionamento.dto.RequestVeiculoDTO;
import com.cleberleao.estacionamento.dto.ResponseVeiculoDTO;
import com.cleberleao.estacionamento.dto.SaidaVeiculoDTO;
import com.cleberleao.estacionamento.service.VeiculoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/veiculos")
@CrossOrigin("*")
public class VeiculoController {

    @Autowired
    private VeiculoService veiculoService;

    @PostMapping
    public ResponseEntity<ResponseVeiculoDTO> cadastrar(@RequestBody RequestVeiculoDTO dto){
        ResponseVeiculoDTO response = veiculoService.cadastrar(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PostMapping("/saida")
    public ResponseEntity<SaidaVeiculoDTO> saida(@RequestParam("placa") String placa){
        SaidaVeiculoDTO dto = veiculoService.saidaPorPlaca(placa);
        return ResponseEntity.ok(dto);
    }
}

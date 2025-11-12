package com.cleberleao.estacionamento.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SaidaVeiculoDTO {
    private String placa;
    private long duracaoHoras;
    private double tarifaPorHora;
    private double totalPagar;
}

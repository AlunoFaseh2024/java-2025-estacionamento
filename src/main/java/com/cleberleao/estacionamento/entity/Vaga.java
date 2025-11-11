package com.cleberleao.estacionamento.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Vaga {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private Integer numero;

    private boolean ocupada = false;

    @OneToOne(mappedBy = "vaga", fetch = FetchType.LAZY)
    private Veiculo veiculo;
}

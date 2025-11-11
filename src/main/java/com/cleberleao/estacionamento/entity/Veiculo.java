package com.cleberleao.estacionamento.entity;

import com.cleberleao.estacionamento.dto.RequestVeiculoDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Veiculo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String placa;

    private String modelo;

    private String marca;

    @Enumerated(EnumType.STRING)
    private TipoVeiculo tipo;

    private LocalDateTime horaEntrada;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    @OneToOne
    @JoinColumn(name = "vaga_id")
    private Vaga vaga;

    public Veiculo(RequestVeiculoDTO dto) {
        this.placa = dto.getPlaca();
        this.modelo = dto.getModelo();
        this.marca = dto.getMarca();
        this.tipo = dto.getTipo();
        this.horaEntrada = LocalDateTime.now();
    }
}

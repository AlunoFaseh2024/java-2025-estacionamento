package com.cleberleao.estacionamento.repository;

import com.cleberleao.estacionamento.entity.Vaga;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface VagaRepository extends JpaRepository<Vaga, Long> {
    Optional<Vaga> findByNumero(Integer numero);
    List<Vaga> findByOcupadaTrue();
    List<Vaga> findByOcupadaFalse();
}

package com.estacionamento.repository;

import com.estacionamento.entity.Vaga;
import com.estacionamento.entity.enums.StatusVaga;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface VagaRepository extends JpaRepository<Vaga, Long> {
    Optional<Vaga> findByCodigo(String codigo);

    Optional<Vaga> findFirstByStatus(StatusVaga statusVaga);
}

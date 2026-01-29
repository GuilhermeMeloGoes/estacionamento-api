package com.estacionamento.repository;

import com.estacionamento.entity.Vagas;
import com.estacionamento.entity.enums.StatusVaga;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface VagaRepository extends JpaRepository<Vagas, Long> {
    Optional<Vagas> findByCodigo(String codigo);

    Optional<Vagas> findFirstByStatus(StatusVaga statusVaga);
}

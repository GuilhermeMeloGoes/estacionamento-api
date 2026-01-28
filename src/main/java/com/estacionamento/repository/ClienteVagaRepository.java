package com.estacionamento.repository;

import com.estacionamento.entity.Carro;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteVagaRepository extends JpaRepository<Carro, Long> {

}

package com.estacionamento.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.estacionamento.entity.Carro;

public interface CarroRepository extends JpaRepository<Carro,Long>{

    Optional<Carro> findById(String placaCarro);
    
}

package com.estacionamento.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.estacionamento.entity.Carro;
import java.util.List;


public interface CarroRepository extends JpaRepository<Carro,Long>{

    Optional<Carro> findByPlacaCarro(String placaCarro);

    
}

package com.estacionamento.service;

import java.util.List;

import javax.management.RuntimeErrorException;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.estacionamento.entity.Carro;
import com.estacionamento.exception.UsernameUniqueViolationException;
import com.estacionamento.repository.CarroRepository;
import com.estacionamento.web.controller.dto.CarroCreateDto;
import com.estacionamento.web.controller.dto.mapper.CarroMapper;

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CarroService {

    private final CarroRepository carroRepository;

    @Transactional
    public Carro createCar (Carro carro){
        
        try{
            return carroRepository.save(carro);
        }catch (DataIntegrityViolationException ex){
            throw new UsernameUniqueViolationException( "A placa informada já existe no banco de dados.");
        }
    }

    @Transactional(readOnly = true)
    public Carro findCarById (String placaCarro) {
        return carroRepository.findByPlacaCarro(placaCarro).orElseThrow(
                () -> new EntityNotFoundException(String.format("Carro de id=%s não encontrado", placaCarro))
        );
    }

    @Transactional(readOnly = true)
    public List<Carro> findAll(){
        return carroRepository.findAll();
    }


    

}

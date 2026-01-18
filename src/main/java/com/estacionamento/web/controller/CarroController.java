package com.estacionamento.web.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.estacionamento.entity.Carro;
import com.estacionamento.service.CarroService;
import com.estacionamento.web.controller.dto.CarroCreateDto;
import com.estacionamento.web.controller.dto.CarroResponseDto;
import com.estacionamento.web.controller.dto.mapper.CarroMapper;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;



@RestController
@RequestMapping("api/v1/carros")
@RequiredArgsConstructor
public class CarroController {
    
    private final CarroService carroService;

    @PostMapping
    public ResponseEntity<CarroResponseDto> createCar(@Valid @RequestBody CarroCreateDto carroCreateDto) {
        Carro carroCriado = carroService.createCar(carroCreateDto);

        CarroResponseDto response = CarroMapper.toCarroResponseDto(carroCriado);
        
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/{placaCarro}")
    public ResponseEntity<CarroResponseDto> findById(@PathVariable String placaCarro) {
        
        Carro carroEncontrado = carroService.findCarById(placaCarro);

        return ResponseEntity.ok(CarroMapper.toCarroResponseDto(carroEncontrado));


    }

    @GetMapping()
    public ResponseEntity<List<CarroResponseDto>> findAllCars() {
        
        List<Carro> carros = carroService.findAll();

        List<CarroResponseDto> response = carros.stream().map(carro -> CarroMapper.toCarroResponseDto(carro))
                .collect(Collectors.toList());

        return ResponseEntity.ok(response);
    }
    
    
    

}

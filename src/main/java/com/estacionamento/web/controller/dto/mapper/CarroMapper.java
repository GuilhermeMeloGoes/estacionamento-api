package com.estacionamento.web.controller.dto.mapper;

import org.modelmapper.ModelMapper;

import com.estacionamento.entity.Carro;
import com.estacionamento.web.controller.dto.CarroCreateDto;
import com.estacionamento.web.controller.dto.CarroResponseDto;

public class CarroMapper {
    
    public static Carro toCarro(CarroCreateDto carroCreateDto){
        return new ModelMapper().map(carroCreateDto, Carro.class);
    }

    public static CarroResponseDto toCarroResponseDto(Carro carro) {
        return new ModelMapper().map(carro, CarroResponseDto.class);
    }

}




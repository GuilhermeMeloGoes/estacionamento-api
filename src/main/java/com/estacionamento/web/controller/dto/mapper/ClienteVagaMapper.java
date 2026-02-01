package com.estacionamento.web.controller.dto.mapper;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;

import com.estacionamento.entity.ClienteVaga;
import com.estacionamento.web.controller.dto.EstacionamentoCreateDto;
import com.estacionamento.web.controller.dto.EstacionamentoResponseDto;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ClienteVagaMapper {
    
    public static ClienteVaga toClienteVaga(EstacionamentoCreateDto estacionamentoCreateDto){
        return new ModelMapper().map(estacionamentoCreateDto, ClienteVaga.class);
    }

    public static EstacionamentoResponseDto toEstacionamentoResponseDto (ClienteVaga clienteVaga) {
        return new ModelMapper().map(clienteVaga, EstacionamentoResponseDto.class);
    }

}




package com.estacionamento.web.controller.dto.mapper;

import com.estacionamento.entity.Cliente;
import com.estacionamento.web.controller.dto.ClienteResponseDto;
import com.estacionamento.web.controller.dto.ClienteCreateDto;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ClienteMapper {

    public static Cliente toCliente(ClienteCreateDto clienteCreateDto) {
        return new ModelMapper().map(clienteCreateDto, Cliente.class);
    }

    public static ClienteResponseDto toClienteResponseDto(Cliente cliente) {
        return new ModelMapper().map(cliente, ClienteResponseDto.class);
    }
}

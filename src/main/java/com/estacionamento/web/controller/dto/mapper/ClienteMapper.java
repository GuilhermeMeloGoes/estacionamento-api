package com.estacionamento.web.controller.dto.mapper;

import com.estacionamento.entity.Cliente;
import com.estacionamento.web.controller.dto.ClienteResponseDto;
import com.estacionamento.web.controller.dto.ClienteCreateDto;
import org.modelmapper.ModelMapper;

public class ClienteMapper {

    public static Cliente toCliente(ClienteCreateDto clienteCreateDto) {
        return new ModelMapper().map(clienteCreateDto, Cliente.class);
    }

    public static ClienteResponseDto toUsuarioResponseDto(Cliente cliente) {
        return new ModelMapper().map(cliente, ClienteResponseDto.class);
    }
}

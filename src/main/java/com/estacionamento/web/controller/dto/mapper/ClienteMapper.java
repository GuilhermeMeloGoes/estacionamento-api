package com.estacionamento.web.controller.dto.mapper;

import com.estacionamento.entity.Cliente;
import com.estacionamento.entity.Usuario;
import com.estacionamento.web.controller.ClienteResponseDto;
import com.estacionamento.web.controller.dto.ClienteCreateDto;
import com.estacionamento.web.controller.dto.UsuarioResponseDto;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;

public class ClienteMapper {

    public static Cliente toCliente(ClienteCreateDto clienteCreateDto) {
        return new ModelMapper().map(clienteCreateDto, Cliente.class);
    }

    public static ClienteResponseDto toUsuarioResponseDto(Cliente cliente) {
        return new ModelMapper().map(cliente, ClienteResponseDto.class);
    }
}

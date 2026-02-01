package com.estacionamento.web.controller.dto.mapper;

import com.estacionamento.entity.Usuario;
import com.estacionamento.web.controller.dto.UsuarioCreateDto;
import com.estacionamento.web.controller.dto.UsuarioResponseDto;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;

import java.util.List;
import java.util.stream.Collectors;

public class UsuarioMapper {

    public static Usuario toUsuario(UsuarioCreateDto usuarioCreateDto) {
        return new ModelMapper().map(usuarioCreateDto, Usuario.class);
    }

    public static UsuarioResponseDto toUsuarioResponseDto(Usuario usuario) {
        String role = usuario.getRole().name().substring("ROLE_".length());
        PropertyMap<Usuario, UsuarioResponseDto> props = new PropertyMap<Usuario, UsuarioResponseDto>() {
            @Override
            protected void configure() {
                map().setRole(role);
            }
        };
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.addMappings(props);
        return modelMapper.map(usuario, UsuarioResponseDto.class);
    }

    public static List<UsuarioResponseDto> toListUsuarioResponseDto(List<Usuario> usuarios) {
       // return usuarios.stream().map(usuario -> toUsuarioResponseDto(usuario)).collect(Collectors.toList());
       return usuarios.stream().map(UsuarioMapper::toUsuarioResponseDto).collect(Collectors.toList());
    }
}

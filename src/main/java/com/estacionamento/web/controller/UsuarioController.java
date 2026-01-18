package com.estacionamento.web.controller;

import com.estacionamento.entity.Usuario;
import com.estacionamento.service.UsuarioService;
import com.estacionamento.web.controller.dto.UsuarioCreateDto;
import com.estacionamento.web.controller.dto.UsuarioResponseDto;
import com.estacionamento.web.controller.dto.UsuarioSenhaDto;
import com.estacionamento.web.controller.dto.mapper.UsuarioMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/usuarios")
@RequiredArgsConstructor
public class UsuarioController {

    private final UsuarioService usuarioService;

    @PostMapping
    public ResponseEntity<UsuarioResponseDto> create(@Valid @RequestBody UsuarioCreateDto usuarioCreateDto) {
       Usuario usuarioCriado = usuarioService.createUser(UsuarioMapper.toUsuario(usuarioCreateDto));
       return ResponseEntity.status(HttpStatus.CREATED).body(UsuarioMapper.toUsuarioResponseDto(usuarioCriado));
    }

    @GetMapping("/{idUsuario}")
    public ResponseEntity<UsuarioResponseDto> findById(@PathVariable Long idUsuario) {
        Usuario usuarioEncontrado = usuarioService.findByIdUser(idUsuario);
        return ResponseEntity.ok(UsuarioMapper.toUsuarioResponseDto(usuarioEncontrado));
    }

    @PatchMapping("/{idUsuario}")
    public ResponseEntity<Void> updatePassword(@PathVariable Long idUsuario, @Valid @RequestBody UsuarioSenhaDto usuarioSenhaDto) {
        usuarioService.updatePasswordUser(idUsuario ,usuarioSenhaDto.getSenhaAtual(), usuarioSenhaDto.getNovaSenha(), usuarioSenhaDto.getConfirmaSenha());
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<UsuarioResponseDto>> findAllUsers() {
        List<Usuario> usuarios = usuarioService.findAllUsers();
        return ResponseEntity.ok(UsuarioMapper.toListUsuarioResponseDto(usuarios));
    }

}

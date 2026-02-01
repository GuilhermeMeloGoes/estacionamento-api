package com.estacionamento.web.controller;

import com.estacionamento.entity.Usuario;
import com.estacionamento.service.UsuarioService;
import com.estacionamento.web.controller.dto.UsuarioCreateDto;
import com.estacionamento.web.controller.dto.UsuarioResponseDto;
import com.estacionamento.web.controller.dto.UsuarioSenhaDto;
import com.estacionamento.web.controller.dto.mapper.UsuarioMapper;
import com.estacionamento.web.controller.exception.ErrorMessage;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Usuários", description = "Contém todas as funcionalidades relativas ao usuário.")
@RestController
@RequestMapping("api/v1/usuarios")
@RequiredArgsConstructor
public class UsuarioController {

    private final UsuarioService usuarioService;

    @Operation(summary = "Criar um novo usuário", description = "Rescurso para criar um usuário.",
            responses = {
                    @ApiResponse(responseCode = "201", description = "Recurso criado com sucesso.",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = UsuarioResponseDto.class))),
                    @ApiResponse(responseCode = "409", description = "O e-mail já está cadastrado no sistema.",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class))),
                    @ApiResponse(responseCode = "422", description = "O e-mail ou senha estão inválidos.",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class))),
            }
    )
    @PostMapping
    public ResponseEntity<UsuarioResponseDto> create(@Valid @RequestBody UsuarioCreateDto usuarioCreateDto) {
        Usuario usuarioCriado = usuarioService.criarUsuario(UsuarioMapper.toUsuario(usuarioCreateDto));
        return ResponseEntity.status(HttpStatus.CREATED).body(UsuarioMapper.toUsuarioResponseDto(usuarioCriado));
    }

    @Operation(summary = "Procurar um usuário por id.", description = "Rescurso para buscar um usuário na base de dados pelo id, caso exista.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Recurso recuperado com sucesso.",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = UsuarioResponseDto.class))),
                    @ApiResponse(responseCode = "404", description = "O usuário não existe no banco de dados.",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class))),
            }
    )
    @GetMapping("/{idUsuario}")
    public ResponseEntity<UsuarioResponseDto> buscarUsuarioPorId(@PathVariable Long idUsuario) {
        Usuario usuarioEncontrado = usuarioService.buscarUsuarioPorId(idUsuario);
        return ResponseEntity.ok(UsuarioMapper.toUsuarioResponseDto(usuarioEncontrado));
    }


    @Operation(summary = "Atualizar a senha de um usuário pelo id.", description = "Rescurso para alterar a senha de um usuário, buscando ele pelo id.",
            responses = {
                    @ApiResponse(responseCode = "204", description = "Recurso atualizado com sucesso.",
                            content = @Content(mediaType = "application/json")),
                    @ApiResponse(responseCode = "404", description = "O usuário não existe no banco de dados.",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class))),
                    @ApiResponse(responseCode = "400", description = "A senha(s) estão inválidas.",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class)))
            }
    )
    @PatchMapping("/{idUsuario}")
    public ResponseEntity<Void> atualizarSenhaUsuario(@PathVariable Long idUsuario, @Valid @RequestBody UsuarioSenhaDto usuarioSenhaDto) {
        usuarioService.atualizarSenhaUsuario(idUsuario, usuarioSenhaDto.getSenhaAtual(), usuarioSenhaDto.getNovaSenha(), usuarioSenhaDto.getConfirmaSenha());
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Retorna os usuários", description = "Retorna todos os usuários, com seus respectivos dados 'id', 'username', 'role', caso não tenha nenhum usuário cadastrado retorna uma lista vazia. ",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Senha atualizada com sucesso",
                            content = @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = UsuarioResponseDto.class))))
            }
    )
    @GetMapping
    public ResponseEntity<List<UsuarioResponseDto>> buscarUsuarios() {
        List<Usuario> usuarios = usuarioService.buscarUsuarios();
        return ResponseEntity.ok(UsuarioMapper.toListUsuarioResponseDto(usuarios));
    }

}

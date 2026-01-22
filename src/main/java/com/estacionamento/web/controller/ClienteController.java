package com.estacionamento.web.controller;

import com.estacionamento.entity.Cliente;
import com.estacionamento.repository.projection.ClienteProjection;
import com.estacionamento.service.ClienteService;
import com.estacionamento.service.UsuarioService;
import com.estacionamento.web.controller.dto.ClienteCreateDto;
import com.estacionamento.web.controller.dto.ClienteResponseDto;
import com.estacionamento.web.controller.dto.PageableDto;
import com.estacionamento.web.controller.dto.mapper.ClienteMapper;
import com.estacionamento.web.controller.dto.mapper.PageableMapper;
import com.estacionamento.web.controller.exception.ErrorMessage;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("api/v1/clientes")
@RequiredArgsConstructor
public class ClienteController {

    private final ClienteService clienteService;
    private final UsuarioService usuarioService;

    @Operation(summary = "Cadastrar o cliente.", description = "Requisição responsavel pelo cadastro do cliente.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Recurso criado com sucesso.",
                            content = @Content(mediaType = "application/json;charset=UTF-8",
                                    schema = @Schema(implementation = ClienteResponseDto.class))),
                    @ApiResponse(responseCode = "409", description = "O CPF do cliente já está cadastrado.",
                            content = @Content(mediaType = "application/json;charset=UTF-8",
                                    schema = @Schema(implementation = ErrorMessage.class)))
            }
    )
    @PostMapping("/{idUsuario}")
    public ResponseEntity<ClienteResponseDto> cadastrarCliente(@RequestBody @Valid ClienteCreateDto clienteDto, @PathVariable Long idUsuario) {
        Cliente cliente = ClienteMapper.toCliente(clienteDto);
        cliente.setUsuario(usuarioService.findByIdUser(idUsuario));
        clienteService.createClient(cliente);

        return ResponseEntity.status(201).body(ClienteMapper.toClienteResponseDto(cliente));
    }

    @Operation(summary = "Buscar cliente por id.", description = "Requisição responsavel por devolver os dados do cliente.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Recurso recuperado com sucesso.",
                            content = @Content(mediaType = "application/json;charset=UTF-8",
                                    schema = @Schema(implementation = ClienteResponseDto.class))),
                    @ApiResponse(responseCode = "404", description = "O cliente não foi encontrado no banco de dados.",
                            content = @Content(mediaType = "application/json;charset=UTF-8",
                                    schema = @Schema(implementation = ErrorMessage.class)))
            }
    )
    @GetMapping("/{idCliente}")
    public ResponseEntity<ClienteResponseDto> buscarClientePorId(@PathVariable Long idCliente) {
        Cliente cliente = clienteService.buscarClientePorId(idCliente);
        return ResponseEntity.status(HttpStatus.OK).body(ClienteMapper.toClienteResponseDto(cliente));
    }


    @Operation(summary = "Listar todos os clientes", description = "Requisição responsavel pela listagem dos clientes cadastrados no banco de dados.",
            parameters = {
                    @Parameter(in = ParameterIn.QUERY, name = "page",
                            content = @Content(schema = @Schema(type = "integer", defaultValue = "0")),
                            description = "Representa a quantidade de páginas que será retornada."),
                    @Parameter(in = ParameterIn.QUERY, name = "size",
                            content = @Content(schema = @Schema(type = "integer", defaultValue = "20")),
                            description = "Representa o número de elementos total por página."),
                    @Parameter(in = ParameterIn.QUERY, name = "sort",
                            content = @Content(schema = @Schema(type = "string", defaultValue = "id,asc")),
                            description = "Representa a ordenação dos elementos na página. Aceita multiplos critérios de ordenação."),

            },
            responses = {
                    @ApiResponse(responseCode = "200", description = "Recurso recuperado com sucesso.",
                            content = @Content(mediaType = "application/json;charset=UTF-8",
                                    schema = @Schema(implementation = PageableDto.class)))
            }
    )
    @GetMapping
    public ResponseEntity<PageableDto> buscarTodosOsClientes(@Parameter(hidden = true)
                                                                 @PageableDefault(size = 5, sort = {"nome"}) Pageable pageable) {
        Page<ClienteProjection> clientes = clienteService.buscarTodosOsClientes(pageable);
        return ResponseEntity.ok(PageableMapper.toDto(clientes));
    }

    @Operation(summary = "Devolve os detalhes do usuários.", description = "Requisição responsavel por devolver os dados do usuário pelo ID.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Recurso recuperado com sucesso.",
                            content = @Content(mediaType = "application/json;charset=UTF-8",
                                    schema = @Schema(implementation = ClienteResponseDto.class)))
            }
    )
    @GetMapping("/detalhes/{idUsuario}")
    public ResponseEntity<ClienteResponseDto> buscarDetalhesCliente(@PathVariable Long idUsuario) {
        Cliente cliente = clienteService.buscarDetalhesClientePorId(idUsuario);
        return ResponseEntity.ok(ClienteMapper.toClienteResponseDto(cliente));
    }

}

package com.estacionamento.web.controller;

import com.estacionamento.entity.Vagas;
import com.estacionamento.service.VagaService;
import com.estacionamento.web.controller.dto.VagaCreateDto;
import com.estacionamento.web.controller.dto.VagaResponseDto;
import com.estacionamento.web.controller.dto.mapper.VagaMapper;
import com.estacionamento.web.controller.exception.ErrorMessage;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.headers.Header;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1/vagas")
public class VagasController {

    private final VagaService vagaService;

    @Operation(summary = "Criar uma nova vaga", description = "Recurso para criar uma nova vaga.",
            responses = {
                    @ApiResponse(responseCode = "201", description = "Vaga foi criada com sucesso.",
                            headers = @Header(name = HttpHeaders.LOCATION, description = "URL do recurso criado.")),
                    @ApiResponse(responseCode = "409", description = "Vaga já cadastrada",
                            content = @Content(mediaType = "application/json;charset=UTF-8", schema = @Schema(implementation = ErrorMessage.class))),
                    @ApiResponse(responseCode = "422", description = "Dados informados inválidos.",
                            content = @Content(mediaType = "application/json;charset=UTF-8", schema = @Schema(implementation = ErrorMessage.class))),
            })
    @PostMapping
    public ResponseEntity<Void> criarVagas(@RequestBody @Valid VagaCreateDto vagasDto) {
        System.out.println(vagasDto);
        Vagas vaga = VagaMapper.toVaga(vagasDto);
        vagaService.criarVaga(vaga);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{codigo}")
                .buildAndExpand(vaga.getCodigo()).toUri();

        return ResponseEntity.created(location).build();
    }

    @Operation(summary = "Localizar uma vaga.", description = "Recurso para localizar uma vaga pelo seu código.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Vaga foi localizada com sucesso.",
                            content = @Content(mediaType = "application/json;charset=UTF-8", schema = @Schema(implementation = VagaResponseDto.class))),
                    @ApiResponse(responseCode = "409", description = "Vaga já cadastrada",
                            content = @Content(mediaType = "application/json;charset=UTF-8", schema = @Schema(implementation = ErrorMessage.class)))
            })

    @GetMapping("/{codigo}")
    public ResponseEntity<VagaResponseDto> buscarVagaPorCodigo(@PathVariable String codigo) {
        Vagas vaga = vagaService.buscarVagaPorCodigo(codigo);
        return ResponseEntity.ok(VagaMapper.toVagaResponseDto(vaga));
    }
}

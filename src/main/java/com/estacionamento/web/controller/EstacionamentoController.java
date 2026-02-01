package com.estacionamento.web.controller;

import com.estacionamento.repository.projection.ClienteVagaProjection;
import com.estacionamento.service.ClienteVagaService;
import com.estacionamento.web.controller.dto.PageableDto;
import com.estacionamento.web.controller.dto.mapper.PageableMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import com.estacionamento.entity.ClienteVaga;
import com.estacionamento.service.EstacionamentoService;
import com.estacionamento.web.controller.dto.EstacionamentoCreateDto;
import com.estacionamento.web.controller.dto.EstacionamentoResponseDto;
import com.estacionamento.web.controller.dto.mapper.ClienteVagaMapper;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import java.net.URI;

import org.springframework.http.ResponseEntity;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;


@RestController
@RequestMapping("api/v1/estacionamentos")
@RequiredArgsConstructor
public class EstacionamentoController {

    private final EstacionamentoService estacionamentoService;
    private final ClienteVagaService clienteVagaService;

    @PostMapping("/check-in")
    public ResponseEntity<EstacionamentoResponseDto> checkin(@Valid @RequestBody EstacionamentoCreateDto estacionamentoCreateDto) {

        ClienteVaga carro = ClienteVagaMapper.toClienteVaga(estacionamentoCreateDto);
        ClienteVaga carroCriado = estacionamentoService.checkIn(carro);
        EstacionamentoResponseDto carroResponseDto = ClienteVagaMapper.toEstacionamentoResponseDto(carroCriado);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{recibo}")
                .buildAndExpand(carro.getRecibo()).toUri();

        return ResponseEntity.created(location).body(carroResponseDto);
    }

    @GetMapping("/check-in/{recibo}")
    public ResponseEntity<EstacionamentoResponseDto> buscarVagaPorRecibo(@PathVariable String recibo) {
        ClienteVaga carro = clienteVagaService.buscarVagaPorRecibo(recibo);
        EstacionamentoResponseDto carroResponseDto = ClienteVagaMapper.toEstacionamentoResponseDto(carro);
        return ResponseEntity.ok(carroResponseDto);
    }

    @PutMapping("/check-out/{recibo}")
    public ResponseEntity<EstacionamentoResponseDto> checkout(@PathVariable String recibo) {
        ClienteVaga carro = estacionamentoService.checkOut(recibo);
        EstacionamentoResponseDto carroResponseDto = ClienteVagaMapper.toEstacionamentoResponseDto(carro);
        return ResponseEntity.ok(carroResponseDto);
    }

    @GetMapping("/cpf/{cpf}")
    public ResponseEntity<PageableDto> buscarEstacionamentosPorCpf(@PathVariable String cpf,
                                                                   @PageableDefault(size = 5, sort = "dataEntrada",
                                                                           direction = Sort.Direction.ASC) Pageable pageable) {

        Page<ClienteVagaProjection> projectionPage = clienteVagaService.buscarEstacionamentosPorCpf(cpf, pageable);
        PageableDto pageableDto = PageableMapper.toDto(projectionPage);
        return ResponseEntity.ok(pageableDto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PageableDto> buscarEstacionamentosCliente(@PathVariable Long id,
                                                                   @PageableDefault(size = 5, sort = "dataEntrada",
                                                                           direction = Sort.Direction.ASC) Pageable pageable) {

        Page<ClienteVagaProjection> projectionPage = clienteVagaService.buscarEstacionamentosClientePorId(id, pageable);
        PageableDto pageableDto = PageableMapper.toDto(projectionPage);
        return ResponseEntity.ok(pageableDto);
    }

}

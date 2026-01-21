package com.estacionamento.web.controller;

import com.estacionamento.entity.Cliente;
import com.estacionamento.service.ClienteService;
import com.estacionamento.service.UsuarioService;
import com.estacionamento.web.controller.dto.ClienteCreateDto;
import com.estacionamento.web.controller.dto.ClienteResponseDto;
import com.estacionamento.web.controller.dto.mapper.ClienteMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
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

    @PostMapping("/{idUsuario}")
    public ResponseEntity<ClienteResponseDto> cadastrarCliente(@RequestBody @Valid ClienteCreateDto clienteDto, @PathVariable Long idUsuario) {
        Cliente cliente = ClienteMapper.toCliente(clienteDto);
        cliente.setUsuario(usuarioService.findByIdUser(idUsuario));
        clienteService.createClient(cliente);

        return ResponseEntity.status(201).body(ClienteMapper.toClienteResponseDto(cliente));
    }

    @GetMapping("/{idCliente}")
    public ResponseEntity<ClienteResponseDto> buscarClientePorId(@PathVariable Long idCliente) {
        Cliente cliente = clienteService.buscarClientePorId(idCliente);
        return ResponseEntity.status(HttpStatus.OK).body(ClienteMapper.toClienteResponseDto(cliente));
    }

    @GetMapping
    public ResponseEntity<Page<Cliente>> buscarTodosOsClientes(Pageable pageable) {
        Page<Cliente> clientes = clienteService.buscarTodosOsClientes(pageable);
        return ResponseEntity.ok(clientes);
    }

}

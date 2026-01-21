package com.estacionamento.service;

import com.estacionamento.entity.Cliente;
import com.estacionamento.entity.Usuario;
import com.estacionamento.exception.CPFUniqueViolationException;
import com.estacionamento.exception.EntityNotFoundException;
import com.estacionamento.exception.UsernameUniqueViolationException;
import com.estacionamento.repository.ClienteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ClienteService {

    private final ClienteRepository clienteRepository;

    @Transactional
    public Cliente createClient(Cliente cliente) {
        try {
            return clienteRepository.save(cliente);
        } catch (DataIntegrityViolationException ex) {
            throw new CPFUniqueViolationException(String.format("O CPF %s, já está cadastrado na base de dados.", cliente.getCpf()));
        }

    }

    @Transactional(readOnly = true)
    public Cliente buscarClientePorId(Long idCliente) {
        return clienteRepository.findById(idCliente).orElseThrow(
                () -> new EntityNotFoundException(String.format("O cliente com o id %s, não está cadastrado no banco de dados.", idCliente))
        );
    }

    @Transactional(readOnly = true)
    public Page<Cliente> buscarTodosOsClientes(Pageable pageable) {
        return  clienteRepository.findAll(pageable);
    }
}

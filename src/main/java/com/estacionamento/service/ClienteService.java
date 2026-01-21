package com.estacionamento.service;

import com.estacionamento.entity.Cliente;
import com.estacionamento.entity.Usuario;
import com.estacionamento.exception.CPFUniqueViolationException;
import com.estacionamento.exception.UsernameUniqueViolationException;
import com.estacionamento.repository.ClienteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

}

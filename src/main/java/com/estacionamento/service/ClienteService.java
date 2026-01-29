package com.estacionamento.service;

import com.estacionamento.entity.Cliente;
import com.estacionamento.exception.CPFUniqueViolationException;
import com.estacionamento.exception.EntityNotFoundException;
import com.estacionamento.repository.ClienteRepository;
import com.estacionamento.repository.projection.ClienteProjection;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

    @Transactional(readOnly = true)
    public Cliente buscarClientePorId(Long idCliente) {
        return clienteRepository.findById(idCliente).orElseThrow(
                () -> new EntityNotFoundException(String.format("O cliente com o id %s, não está cadastrado no banco de dados.", idCliente))
        );
    }

    @Transactional(readOnly = true)
    public Page<ClienteProjection> buscarTodosOsClientes(Pageable pageable) {
        return clienteRepository.findAllPageble(pageable);
    }

    @Transactional(readOnly = true)
    public Cliente buscarDetalhesClientePorId(Long idUsuario) {
        Cliente cliente = clienteRepository.findByUsuarioId(idUsuario);

        if (cliente == null) {
            throw new EntityNotFoundException(String.format("O cliente com o id %s, não está cadastrado no banco de dados.", idUsuario));
        }

        return cliente;
    }

    @Transactional(readOnly = true)
    public Cliente buscarClientePorCpf(String cpf) {
        Cliente cliente = clienteRepository.findByCpf(cpf).orElseThrow(
                () -> new EntityNotFoundException(String.format("Cliente com o cpf %s, não encontrado.", cpf))
        );
        return cliente;
    }
}

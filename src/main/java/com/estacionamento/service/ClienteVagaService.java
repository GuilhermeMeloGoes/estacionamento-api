package com.estacionamento.service;

import com.estacionamento.entity.ClienteVaga;
import com.estacionamento.exception.EntityNotFoundException;
import com.estacionamento.repository.ClienteVagaRepository;
import com.estacionamento.repository.projection.ClienteVagaProjection;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ClienteVagaService {

    private final ClienteVagaRepository clienteVagaRepository;

    @Transactional
    public ClienteVaga criarVaga(ClienteVaga carro) {
        return clienteVagaRepository.save(carro);
    }

    @Transactional(readOnly = true)
    public ClienteVaga buscarVagaPorRecibo(String recibo) {
        return clienteVagaRepository.findByReciboAndDataSaidaIsNull(recibo).orElseThrow(
                () -> new EntityNotFoundException(String.format("Recibo '%s' não encontrado no sistema ou check-out já realizado", recibo))
        );
    }

    @Transactional(readOnly = true)
    public long getTotalDeVezesEstacionamentoCompleto(String cpf) {
        return clienteVagaRepository.countByClienteCpfAndDataSaidaIsNotNull(cpf);
    }

    @Transactional(readOnly = true)
    public Page<ClienteVagaProjection> buscarEstacionamentosPorCpf(String cpf, Pageable pageable) {
        return clienteVagaRepository.findAllByClienteCpf(cpf, pageable);
    }

    public Page<ClienteVagaProjection> buscarEstacionamentosClientePorId(Long id, Pageable pageable) {
        return clienteVagaRepository.findAllByClienteUsuarioId(id, pageable);
    }
}

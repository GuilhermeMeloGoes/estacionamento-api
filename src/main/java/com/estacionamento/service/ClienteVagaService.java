package com.estacionamento.service;

import com.estacionamento.entity.Carro;
import com.estacionamento.repository.ClienteVagaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ClienteVagaService {

    private final ClienteVagaRepository clienteVagaRepository;

    @Transactional
    public Carro criarVaga(Carro carro) {
        return clienteVagaRepository.save(carro);
    }


}

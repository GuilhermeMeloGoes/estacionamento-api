package com.estacionamento.service;

import java.time.LocalDateTime;
import java.util.List;

import javax.management.RuntimeErrorException;

import com.estacionamento.entity.Cliente;
import com.estacionamento.entity.Vagas;
import com.estacionamento.entity.enums.StatusVaga;
import com.estacionamento.utils.CarroUtils;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.estacionamento.entity.Carro;
import com.estacionamento.exception.UsernameUniqueViolationException;
import com.estacionamento.repository.CarroRepository;
import com.estacionamento.web.controller.dto.CarroCreateDto;
import com.estacionamento.web.controller.dto.mapper.CarroMapper;

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CarroService {

    private final ClienteVagaService clienteVagaService;
    private final ClienteService clienteService;
    private final VagaService vagaService;

    @Transactional
    public Carro checkIn(Carro carro) {
        Cliente cliente = clienteService.buscarClientePorCpf(carro.getCliente().getCpf());
        carro.setCliente(cliente);

        Vagas vaga = vagaService.buscarPorVagaLivre();
        vaga.setStatus(StatusVaga.OCUPADA);
        carro.setVaga(vaga);

        carro.setDataEntrada(LocalDateTime.now());
        carro.setRecibo(CarroUtils.gerarRecibo());

        return clienteVagaService.criarVaga(carro);
    }

    @Transactional(readOnly = true)
    public Carro findCarById(String placaCarro) {
        return carroRepository.findByPlacaCarro(placaCarro).orElseThrow(
                () -> new EntityNotFoundException(String.format("Carro de id=%s não encontrado", placaCarro))
        );
    }

    @Transactional(readOnly = true)
    public List<Carro> findAll() {
        return carroRepository.findAll();
    }


}

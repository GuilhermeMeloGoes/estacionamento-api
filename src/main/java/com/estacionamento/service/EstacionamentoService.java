package com.estacionamento.service;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.estacionamento.entity.Cliente;
import com.estacionamento.entity.Vaga;
import com.estacionamento.entity.enums.StatusVaga;
import com.estacionamento.utils.EstacionamentoUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.estacionamento.entity.ClienteVaga;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EstacionamentoService {

    private final ClienteVagaService clienteVagaService;
    private final ClienteService clienteService;
    private final VagaService vagaService;

    @Transactional
    public ClienteVaga checkIn(ClienteVaga carro) {
        Cliente cliente = clienteService.buscarClientePorCpf(carro.getCliente().getCpf());
        carro.setCliente(cliente);

        Vaga vaga = vagaService.buscarPorVagaLivre();
        vaga.setStatus(StatusVaga.OCUPADA);
        carro.setVaga(vaga);

        carro.setDataEntrada(LocalDateTime.now());
        carro.setRecibo(EstacionamentoUtils.gerarRecibo(vaga.getCodigo()));

        return clienteVagaService.criarVaga(carro);
    }

    @Transactional
    public ClienteVaga checkOut(String recibo) {
        ClienteVaga clienteVaga = clienteVagaService.buscarVagaPorRecibo(recibo);
        LocalDateTime dataSaida = LocalDateTime.now();

        BigDecimal valor = EstacionamentoUtils.calcularCusto(clienteVaga.getDataEntrada(), dataSaida);
        clienteVaga.setValor(valor);

        long totalDeVezesEstacionadas = clienteVagaService.getTotalDeVezesEstacionamentoCompleto(clienteVaga.getCliente().getCpf());
        BigDecimal desconto = EstacionamentoUtils.calcularDesconto(valor, totalDeVezesEstacionadas);
        clienteVaga.setValorDesconto(desconto);
        clienteVaga.setDataSaida(dataSaida);

        clienteVaga.getVaga().setStatus(StatusVaga.LIVRE);
        return clienteVagaService.criarVaga(clienteVaga);
    }
}

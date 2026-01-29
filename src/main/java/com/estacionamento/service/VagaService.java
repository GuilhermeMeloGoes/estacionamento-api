package com.estacionamento.service;

import com.estacionamento.entity.Vagas;
import com.estacionamento.exception.CodigoUniqueViolationException;
import com.estacionamento.exception.EntityNotFoundException;
import com.estacionamento.repository.VagaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.estacionamento.entity.enums.StatusVaga.LIVRE;

@Service
@RequiredArgsConstructor
public class VagaService {

    private final VagaRepository vagaRepository;

    @Transactional
    public Vagas criarVaga(Vagas vagas) {
        try {
            return vagaRepository.save(vagas);
        } catch (DataIntegrityViolationException e) {
            throw new CodigoUniqueViolationException(String.format("Vaga com o código %s, já foi cadastrada!", vagas.getCodigo()));
        }
    }

    @Transactional(readOnly = true)
    public Vagas buscarVagaPorCodigo(String codigo) {
        return vagaRepository.findByCodigo(codigo).orElseThrow(
                () -> new EntityNotFoundException(String.format("Vaga com o código %s, não encontrada.", codigo))
        );
    }

    @Transactional(readOnly = true)
    public Vagas buscarPorVagaLivre() {
        return vagaRepository.findFirstByStatus(LIVRE).orElseThrow(
                () -> new EntityNotFoundException(String.format("Estacionamento está lotado."))
                );
    }
}

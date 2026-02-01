package com.estacionamento.service;

import com.estacionamento.entity.Vaga;
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
    public Vaga criarVaga(Vaga vaga) {
        try {
            return vagaRepository.save(vaga);
        } catch (DataIntegrityViolationException e) {
            throw new CodigoUniqueViolationException(String.format("Vaga com o código %s, já foi cadastrada!", vaga.getCodigo()));
        }
    }

    @Transactional(readOnly = true)
    public Vaga buscarVagaPorCodigo(String codigo) {
        return vagaRepository.findByCodigo(codigo).orElseThrow(
                () -> new EntityNotFoundException(String.format("Vaga com o código %s, não encontrada.", codigo))
        );
    }

    @Transactional(readOnly = true)
    public Vaga buscarPorVagaLivre() {
        return vagaRepository.findFirstByStatus(LIVRE).orElseThrow(
                () -> new EntityNotFoundException(String.format("Estacionamento está lotado."))
        );
    }
}

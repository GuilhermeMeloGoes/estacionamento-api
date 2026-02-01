package com.estacionamento.web.controller.dto.mapper;

import com.estacionamento.entity.Vaga;
import com.estacionamento.web.controller.dto.VagaCreateDto;
import com.estacionamento.web.controller.dto.VagaResponseDto;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class VagaMapper {

    public static Vaga toVaga(VagaCreateDto vagaResponseDto) {
        return new ModelMapper().map(vagaResponseDto, Vaga.class);
    }

    public static VagaResponseDto toVagaResponseDto(Vaga vagas) {
        return new ModelMapper().map(vagas, VagaResponseDto.class);
    }
}

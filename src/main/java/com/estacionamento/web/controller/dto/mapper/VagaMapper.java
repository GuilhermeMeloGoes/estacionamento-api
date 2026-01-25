package com.estacionamento.web.controller.dto.mapper;

import com.estacionamento.entity.Usuario;
import com.estacionamento.entity.Vagas;
import com.estacionamento.web.controller.dto.VagaResponseDto;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class VagaMapper {

    public static Vagas toVaga(VagaResponseDto vagaResponseDto) {
        return new ModelMapper().map(vagaResponseDto, Vagas.class);
    }

    public static VagaResponseDto toVagaResponseDto(Vagas vagas) {
        return new ModelMapper().map(vagas, VagaResponseDto.class);
    }
}

package com.estacionamento.web.controller.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.hibernate.validator.constraints.br.CPF;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class EstacionamentoCreateDto {

    @NotBlank
    @Pattern(message = "Formato da placa inválido, ela pode assumir o padrão 'XXX-1234' ou 'XXX 4X44'.", regexp = "^[A-Z]{3}-[0-9][A-Z0-9][0-9]{2}$")
    @Size(min = 8, max = 8)
    private String placaCarro;
    
    @NotBlank
    private String marcaCarro;

    @NotBlank
    private String modeloCarro;

    @NotBlank
    private String corCarro;

    @NotBlank
    @Size(min = 11, max = 11)
    @CPF
    private String clienteCpf;
}

package com.estacionamento.web.controller.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Setter
public class CarroCreateDto {

    @NotBlank
    @Pattern(message = "Formato da placa inválido.", regexp = "^[A-Z]{3}[0-9][A-Z][0-9]{2}$")
    @Size(min = 7, max = 7)
    private String placaCArro;
    
    @NotBlank
    private String marcaCarro;

    @NotBlank
    private String modeloCarro;

    @NotBlank
    private String corCarro;


    
}

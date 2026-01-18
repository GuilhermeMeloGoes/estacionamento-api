package com.estacionamento.web.controller.dto;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CarroResponseDto {
    
    private Long id;
    
    private String marcaCarro;

    private String modeloCarro;

    private String corCarro;

    private String placaCarro;
}

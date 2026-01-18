package com.estacionamento.web.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Setter
public class CarroResponseDto {
    
    private Long id;
    
    private String marcaCarro;

    private String modeloCarro;

    private String corCarro;

    private String placaCarro;
}

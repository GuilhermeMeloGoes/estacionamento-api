package com.estacionamento.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "carros")
@Data
@NoArgsConstructor
public class Carro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(table = "id")
    private Long id;

    @Column(name = "marca_carro", nullable = true, length = 100)
    private String marcaCarro;

    @Column(name = "modelo_carro", nullable = true, length = 100)
    private String modeloCarro;

    @Column(name = "cor_carro", nullable = true, length = 100)
    private String corCarro;

    @Column(name = "placa_carro", nullable = false, length = 100)
    private String placaCarro;


    // Campos de auditoria
    @Column(name = "data_criacao")
    private LocalDateTime dataCriacao;

    @Column(name = "data_modificacao")
    private LocalDateTime dataModificacao;

    @Column(name = "criado_por")
    private String criadoPor;

    @Column(name = "modificado_por")
    private String modificadoPor;



}

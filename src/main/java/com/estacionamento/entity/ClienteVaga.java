package com.estacionamento.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "clientes_tem_vagas")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ClienteVaga {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    @Column(name = "numero_recibo", nullable = false, unique = true, length = 15)
    private String recibo;

    @Column(name = "placa_carro", nullable = false, length = 8)
    private String placaCarro;

    @Column(name = "marca_carro", nullable = false, length = 45)
    private String marcaCarro;

    @Column(name = "modelo_carro", nullable = false, length = 45)
    private String modeloCarro;

    @Column(name = "cor_carro", nullable = false, length = 45)
    private String corCarro;

    @Column(name = "data_entrada", nullable = false)
    private LocalDateTime dataEntrada;

    @Column(name = "data_saida")
    private LocalDateTime dataSaida;

    @Column(name = "valor_carro", columnDefinition = "decimal(7,2)")
    private BigDecimal valor;

    @Column(name = "valor_desconto", columnDefinition = "decimal(7,2)")
    private BigDecimal valorDesconto;

    @ManyToOne
    @JoinColumn(name = "id_cliente", nullable = false)
    private Cliente cliente;

    @ManyToOne
    @JoinColumn(name = "id_vaga", nullable = false)
    private Vaga vaga;

    @Column(name = "data_criacao")
    private LocalDateTime dataCriacao;

    @Column(name = "data_modificacao")
    private LocalDateTime dataModificacao;

    @Column(name = "criado_por")
    private String criadoPor;

    @Column(name = "modificado_por")
    private String modificadoPor;


    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        ClienteVaga carro = (ClienteVaga) o;
        return Objects.equals(id, carro.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}

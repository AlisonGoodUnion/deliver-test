package com.project.deliver.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;


@AllArgsConstructor
@Builder
@Data
@NoArgsConstructor
@Entity
@Table(name = "CONTA")
public class Conta {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "NOME")
    private String nome;
    @Column(name = "VALOR_ORIGINAL")
    private BigDecimal valorOriginal;
    @Column(name = "VALOR_CORRIGIDO")
    private BigDecimal valorCorrigido;
    @Column(name = "VENCIMENTO")
    private LocalDate vencimento;
    @Column(name = "PAGAMENTO")
    private LocalDate pagamento;
    @Column(name = "DIAS_ATRASO")
    private Integer diasAtraso;

    @ManyToOne
    @JoinColumn(name = "ATRASO", columnDefinition = "ID")
    private Atraso atraso;
}

package com.project.deliver.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Entity
@Table(name = "ATRASO")
public class Atraso {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "DIAS_ATRASO")
    private String diasAtraso;
    @Column(name = "MULTA")
    private String multa;
    @Column(name = "JUROS_DIA")
    private BigDecimal juros;
}

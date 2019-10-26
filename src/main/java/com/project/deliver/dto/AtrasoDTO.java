package com.project.deliver.dto;

import com.project.deliver.domain.Atraso;
import lombok.Builder;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

@Data
@Builder
public class AtrasoDTO implements Serializable {
    private static final long serialVersionUID = 3378671214771553555L;

    @Id
    @Column(name = "ID")
    private Long id;
    @Column(name = "DIAS_ATRASO")
    private String diasAtraso;
    @Column(name = "MULTA")
    private String multa;
    @Column(name = "JUROS_DIA")
    private BigDecimal juros;

    public Atraso toEntity() {
        return Atraso.builder()
                .id(id)
                .diasAtraso(diasAtraso)
                .multa(multa)
                .juros(juros)
                .build();
    }

    public static AtrasoDTO valueOf(Atraso atraso) {
        if (Objects.isNull(atraso)) {
            return null;
        }

        return AtrasoDTO.builder()
                .id(atraso.getId())
                .diasAtraso(atraso.getDiasAtraso())
                .multa(atraso.getMulta())
                .juros(atraso.getJuros())
                .build();

    }
}

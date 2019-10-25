package com.project.deliver.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.project.deliver.domain.Conta;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class ContaDTO implements Serializable {
    private static final long serialVersionUID = 8106354023976241635L;

    @JsonIgnore
    private Long id;
    private String nome;
    private BigDecimal valorOriginal;
    private BigDecimal valorCorrigido;
    @JsonIgnore
    @JsonDeserialize(using = LocalDateDeserializer.class)
    private LocalDate vencimento;
    private Integer diasAtraso;
    @JsonDeserialize(using = LocalDateDeserializer.class)
    private LocalDate pagamento;
    private AtrasoDTO atraso;


    public Conta toEndity() {

        return Conta.builder()
                .id(id)
                .nome(nome)
                .valorOriginal(valorOriginal)
                .valorCorrigido(valorCorrigido)
                .vencimento(vencimento)
                .pagamento(pagamento)
                .diasAtraso(diasAtraso)
                .build();
    }

    public static ContaDTO valueOf(Conta conta) {
        return ContaDTO.builder()
                .id(conta.getId())
                .nome(conta.getNome())
                .valorOriginal(conta.getValorOriginal())
                .valorCorrigido(conta.getValorCorrigido())
                .vencimento(conta.getVencimento())
                .pagamento(conta.getPagamento())
                .diasAtraso(conta.getDiasAtraso())
                .build();
    }
}

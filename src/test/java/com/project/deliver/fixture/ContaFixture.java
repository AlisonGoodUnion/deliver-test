package com.project.deliver.fixture;

import com.project.deliver.domain.Conta;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.RandomUtils;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * Classe utilizada para criar contas com valores random.
 */
public class ContaFixture {

    private final Conta.ContaBuilder builder;

    public ContaFixture() {
        builder = Conta.builder();
    }

    public static ContaFixture get() {
        return new ContaFixture();
    }

    public ContaFixture random() {
        builder.nome(RandomStringUtils.randomAlphabetic(10))
                .valorOriginal(BigDecimal.valueOf(RandomUtils.nextLong(0, 100)))
                .pagamento(LocalDate.now())
                .vencimento(LocalDate.now());
        return this;
    }

    public ContaFixture vencimento(LocalDate vencimento) {
        builder.vencimento(vencimento);
        return this;
    }

    public Conta build() {
        return builder.build();
    }
}

package com.project.deliver.validator;

import com.project.deliver.domain.Conta;
import com.project.deliver.exception.BusinessExceltion;
import org.springframework.stereotype.Component;

import java.util.function.Consumer;

import static java.util.Objects.isNull;
import static org.apache.commons.lang3.StringUtils.isBlank;

@Component
public class ContaValidator implements Consumer<Conta> {

    @Override
    public void accept(Conta conta) {

        if (isNull(conta)) {
            throw new BusinessExceltion("[Conta]: Inválida");
        }
        if (isBlank(conta.getNome())) {
            throw new BusinessExceltion("[Conta]: Nome não informado");
        }
        if (isNull(conta.getValorOriginal())) {
            throw new BusinessExceltion("[Conta]: Valor Original inválido");
        }
        if (isNull(conta.getVencimento())) {
            throw new BusinessExceltion("[Conta]: Vencimento inválido");
        }
        if (isNull(conta.getPagamento())) {
            throw new BusinessExceltion("[Conta]: Data de Pagamento inválida");
        }
    }
}

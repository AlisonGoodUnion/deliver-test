package com.project.deliver.service;

import com.project.deliver.domain.Atraso;
import com.project.deliver.domain.Conta;
import com.project.deliver.exception.BusinessExceltion;
import com.project.deliver.repository.AtrasoRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.temporal.ChronoUnit;
import java.util.Optional;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

@Slf4j
@Service
public class AtrasoService {

    private AtrasoRepository atrasoRepository;

    @Autowired
    public AtrasoService(AtrasoRepository atrasoRepository) {
        this.atrasoRepository = atrasoRepository;
    }

    public void verificar(Conta conta) {
        log.info("verificar atraso");
        if (isNull(conta)) {
            throw new BusinessExceltion("Conta não informada");
        }

        if (conta.getPagamento().isAfter(conta.getVencimento())) {
            Long diasEmAtraso = getDiasEmAtraso(conta);
            conta.setDiasAtraso(Integer.valueOf(diasEmAtraso.toString()));
            log.info("Atraso: " + diasEmAtraso);
        }
    }

    private long getDiasEmAtraso(Conta conta) {
        return ChronoUnit.DAYS.between(conta.getVencimento(), conta.getPagamento());
    }

    public void aplicarMulta(Conta conta) {

        if (nonNull(conta.getDiasAtraso()) && conta.getDiasAtraso() > 0) {
            if (conta.getDiasAtraso() <= 3) {
                Optional<Atraso> atraso = atrasoRepository.findById(1L);
                popularAtraso(conta, atraso);
            }

            if (conta.getDiasAtraso() > 3 && conta.getDiasAtraso() <= 5) {
                Optional<Atraso> atraso = atrasoRepository.findById(2L);
                popularAtraso(conta, atraso);
            }

            if (conta.getDiasAtraso() > 5) {
                Optional<Atraso> atraso = atrasoRepository.findById(3L);
                popularAtraso(conta, atraso);
            }

            if (nonNull(conta.getAtraso())) {
                calcularCorrecao(conta);
            }
        }
    }

    private void popularAtraso(Conta conta, Optional<Atraso> atraso) {
        conta.setAtraso(atraso.isPresent() ? atraso.get() : null);
    }

    private void calcularCorrecao(Conta conta) {
        BigDecimal bd100 = BigDecimal.valueOf(100);
        BigDecimal valor = conta.getValorOriginal().setScale(2);
        BigDecimal diasAtraso = BigDecimal.valueOf(conta.getDiasAtraso());
        BigDecimal multa = conta.getAtraso().getMulta().divide(bd100).setScale(2);
        BigDecimal juros = conta.getAtraso().getJuros().setScale(2);
        BigDecimal jurosAtraso = diasAtraso.multiply(juros).setScale(2);
        BigDecimal multaAtraso = valor.multiply(multa).setScale(2);
        BigDecimal jurosAplicado = valor.multiply(jurosAtraso).divide(bd100).setScale(2);

        log.debug("[MULTA] = " + valor + "$ * " + multa + "MULTA = " + multaAtraso);
        log.debug("[JUROS/DIA] = " + diasAtraso + "DIAS * " + juros + "JUROS = " + jurosAtraso);

        conta.setValorCorrigido(valor.add(multaAtraso).add(jurosAplicado));

        conta.setCalculoAtraso("VALOR: " + valor + "MULTA: " + multaAtraso + " + Juros/dia: " + jurosAplicado + " TOTAL = " + conta.getValorCorrigido() + "R$");
    }
}
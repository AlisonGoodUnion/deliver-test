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

        calcularCorrecao(conta);
    }

    private void popularAtraso(Conta conta, Optional<Atraso> atraso) {
        conta.setAtraso(atraso.isPresent() ? atraso.get() : null);
    }

    private void calcularCorrecao(Conta conta) {

//        DEVENDO 130 POR 15 DIAS
//        130 * 5 = 6, 5
//        130 * 4, 5 = 5, 85
//        130 + 6, 5 + 5, 85

        BigDecimal valor = conta.getValorOriginal();
        BigDecimal diasAtraso = BigDecimal.valueOf(conta.getDiasAtraso());
        BigDecimal multa = conta.getAtraso().getMulta().divide(BigDecimal.valueOf(100));

        log.info("[MULTA] = " + valor + "$ * " + multa + "MULTA = " + valor.multiply(multa));
        log.info("[JUROS/DIA] = " + diasAtraso + "DIAS * " + conta.getAtraso().getJuros() + "JUROS = " + diasAtraso.multiply(conta.getAtraso().getJuros()));


        BigDecimal multaAplicada = valor.add(valor.multiply(multa));
        BigDecimal jurosAplicado = valor.multiply(diasAtraso.multiply(conta.getAtraso().getJuros()));
//TODO CORRIGIR VALOR CORRIGIDO
        conta.setValorCorrigido(valor.add(multaAplicada).add(jurosAplicado));
    }
}

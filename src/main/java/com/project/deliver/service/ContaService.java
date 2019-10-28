package com.project.deliver.service;

import com.project.deliver.domain.Conta;
import com.project.deliver.repository.ContaRepository;
import com.project.deliver.validator.ContaValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class ContaService {

    private ContaRepository contaRepository;
    private ContaValidator contaValidator;
    private AtrasoService atrasoService;

    @Autowired
    public ContaService(ContaRepository contaRepository,
                        ContaValidator contaValidator,
                        AtrasoService atrasoService) {
        this.contaRepository = contaRepository;
        this.contaValidator = contaValidator;
        this.atrasoService = atrasoService;
    }

    public Conta incluir(Conta conta) {
        log.info("incluir conta");
        contaValidator.accept(conta);
        atrasoService.verificar(conta);
        atrasoService.aplicarMulta(conta);
        log.info("salvar conta");
        return contaRepository.save(conta);
    }

    public List<Conta> buscar() {
        return contaRepository.findAll();
    }
}

package com.project.deliver.service;

import com.project.deliver.domain.Conta;
import com.project.deliver.repository.ContaRepository;
import com.project.deliver.validator.ContaValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContaService {

    private ContaRepository contaRepository;
    private ContaValidator contaValidator;

    @Autowired
    public ContaService(ContaRepository contaRepository,
                        ContaValidator contaValidator) {
        this.contaRepository = contaRepository;
        this.contaValidator = contaValidator;
    }

    public Conta incluir(Conta conta) {
        contaValidator.accept(conta);
        return contaRepository.save(conta);
    }

    public List<Conta> buscar() {
        return contaRepository.findAll();
    }
}

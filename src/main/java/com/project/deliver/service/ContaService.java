package com.project.deliver.service;

import com.project.deliver.domain.Conta;
import com.project.deliver.repository.ContaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContaService {

    private ContaRepository contaRepository;

    @Autowired
    public ContaService(ContaRepository contaRepository) {
        this.contaRepository = contaRepository;
    }

    public Conta incluir(Conta conta) {
        return contaRepository.save(conta);
    }

    public List<Conta> buscar() {
        return contaRepository.findAll();
    }
}

package com.project.deliver.controller;

import com.project.deliver.domain.Conta;
import com.project.deliver.dto.ContaDTO;
import com.project.deliver.service.ContaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RequestMapping("/conta")
@RestController
public class ContaController {

    private ContaService contaService;

    @Autowired
    public ContaController(ContaService contaService) {
        this.contaService = contaService;
    }

    //TODO IMPLEMENTAR DOCUMENTACAO ENDPOINT VIA SWAGGER.
    @PostMapping
    public ResponseEntity inserir(@RequestBody final ContaDTO conta) {
        Conta contaEntity = contaService.incluir(conta.toEndity());
        ContaDTO contaDTO = ContaDTO.valueOf(contaEntity);
        return ResponseEntity.ok(contaDTO);
    }

    @GetMapping
    public ResponseEntity obter() {
        List<ContaDTO> contas = contaService.buscar()
                .stream()
                .map(ContaDTO::valueOf)
                .collect(Collectors.toList());
        return ResponseEntity.ok(contas);
    }

}

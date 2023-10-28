package br.com.ibm.orcamento.rest.controller;

import br.com.ibm.orcamento.service.LancamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/lancamento")
public class LancamentoController {

    @Autowired
    LancamentoService lancamentoService;

    @GetMapping
    public ResponseEntity<List<LancamentoDto>> findAll() {
        List<LancamentoDto>lancamentoDtoList = lancamentoService.ObterTodos();
        return ResponseEntity.ok().body(lancamentoDtoList);
    }
}
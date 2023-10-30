package br.com.ibm.orcamento.rest.controller;

import br.com.ibm.orcamento.rest.dto.GrupoDespesaDto;
import br.com.ibm.orcamento.rest.dto.LancamentoDto;
import br.com.ibm.orcamento.rest.form.GrupoDespesaForm;
import br.com.ibm.orcamento.rest.form.LancamentoForm;
import br.com.ibm.orcamento.rest.form.LancamentoUpdateForm;
import br.com.ibm.orcamento.service.LancamentoService;
import br.com.ibm.orcamento.service.exceptions.ConstraintException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping("/lancamento")
public class LancamentoController {

    @Autowired
    LancamentoService lancamentoService;

    @GetMapping
    public ResponseEntity<List<LancamentoDto>> findAll() {
        List<LancamentoDto> lancamentoDtoList = lancamentoService.ObterTodos();
        return ResponseEntity.ok().body(lancamentoDtoList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<LancamentoDto> find(@PathVariable("id") int id) {
        LancamentoDto lancamentoDto = lancamentoService.ObterPorId(id);
        return ResponseEntity.ok().body(lancamentoDto);
    }

    @PostMapping
    public ResponseEntity<LancamentoDto> insert(@Valid @RequestBody LancamentoForm lancamentoForm, BindingResult br) {
        if (br.hasErrors())
            throw new ConstraintException(br.getAllErrors().get(0).getDefaultMessage());

        LancamentoDto lancamentoDto = lancamentoService.SalvarLancamento(lancamentoForm);
        return ResponseEntity.ok().body(lancamentoDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<LancamentoDto> update(@Valid @RequestBody LancamentoUpdateForm lancamentoUpdateForm
            , @PathVariable("id") int id, BindingResult br) {
        if (br.hasErrors())
            throw new ConstraintException(br.getAllErrors().get(0).getDefaultMessage());

        LancamentoDto lancamentoDto = lancamentoService.AtualizarLancamento(lancamentoUpdateForm, id);
        return ResponseEntity.ok().body(lancamentoDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") int id) {
        lancamentoService.RemoverLancamento(id);
        return ResponseEntity.noContent().build();
    }
}
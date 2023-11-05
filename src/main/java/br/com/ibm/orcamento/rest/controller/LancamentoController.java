package br.com.ibm.orcamento.rest.controller;

import br.com.ibm.orcamento.rest.dto.LancamentoDto;
import br.com.ibm.orcamento.rest.form.LancamentoForm;
import br.com.ibm.orcamento.service.LancamentoService;
import br.com.ibm.orcamento.service.exceptions.ConstraintException;
import io.swagger.annotations.ApiOperation;
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

    @ApiOperation(value = "Retorna uma lista de Lançamentos")
    @GetMapping
    public ResponseEntity<List<LancamentoDto>> findAll() {
        List<LancamentoDto> lancamentoDtoList = lancamentoService.ObterTodos();
        return ResponseEntity.ok().body(lancamentoDtoList);
    }

    @ApiOperation(value = "Retorna um Lançamento pelo id")
    @GetMapping("/{id}")
    public ResponseEntity<LancamentoDto> find(@PathVariable("id") int id) {
        LancamentoDto lancamentoDto = lancamentoService.ObterPorId(id);
        return ResponseEntity.ok().body(lancamentoDto);
    }

    @ApiOperation(value = "Inserir um Lançamento")
    @PostMapping
    public ResponseEntity<LancamentoDto> insert(@Valid @RequestBody LancamentoForm lancamentoForm, BindingResult br) {
        if (br.hasErrors())
            throw new ConstraintException(br.getAllErrors().get(0).getDefaultMessage());

        LancamentoDto lancamentoDto = lancamentoService.SalvarLancamento(lancamentoForm);
        return ResponseEntity.ok().body(lancamentoDto);
    }

    @ApiOperation(value = "Atualizar um Lançamento pelo id")
    @PutMapping("/{id}")
    public ResponseEntity<LancamentoDto> update(@RequestBody LancamentoForm lancamentoUpdateForm
            , @PathVariable("id") int id, BindingResult br) {
        if (br.hasErrors())
            throw new ConstraintException(br.getAllErrors().get(0).getDefaultMessage());

        LancamentoDto lancamentoDto = lancamentoService.AtualizarLancamento(lancamentoUpdateForm, id);
        return ResponseEntity.ok().body(lancamentoDto);
    }

    @ApiOperation(value = "Excluir um Lançamento pelo id")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") int id) {
        lancamentoService.RemoverLancamento(id);
        return ResponseEntity.noContent().build();
    }
}
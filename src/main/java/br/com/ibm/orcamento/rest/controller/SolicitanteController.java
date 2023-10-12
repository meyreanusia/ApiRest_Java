package br.com.ibm.orcamento.rest.controller;

import br.com.ibm.orcamento.rest.dto.SolicitanteDto;
import br.com.ibm.orcamento.rest.dto.UnidadeDto;
import br.com.ibm.orcamento.rest.form.SolicitanteForm;
import br.com.ibm.orcamento.rest.form.UnidadeForm;
import br.com.ibm.orcamento.service.SolicitanteService;
import br.com.ibm.orcamento.service.exceptions.ConstraintException;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/solicitante")
public class SolicitanteController {

    @Autowired
    private SolicitanteService solicitanteService;

    @ApiOperation(value = "Retorna uma lista de Solicitantes") //Anotação para descrever o endpoint
    @GetMapping
    public ResponseEntity<List<SolicitanteDto>> findAll() {
        List<SolicitanteDto> solicitanteDtoList = solicitanteService.ObterTodos();
        return ResponseEntity.ok().body(solicitanteDtoList);
    }

    @ApiOperation(value = "Retorna um Solicitante pelo id") //Anotação para descrever o endpoint
    @GetMapping("/{id}")
    public ResponseEntity<SolicitanteDto> find(@PathVariable("id") int id) {
        SolicitanteDto solicitanteDto = solicitanteService.ObterPorId(id);
        return ResponseEntity.ok().body(solicitanteDto);
    }

    @ApiOperation(value = "Inserir um Solicitante") //Anotação para descrever o endpoint
    @PostMapping
    public ResponseEntity<SolicitanteDto> insert(@Valid @RequestBody SolicitanteForm solicitanteForm, BindingResult br) {
        if (br.hasErrors())
            throw new ConstraintException(br.getAllErrors().get(0).getDefaultMessage());

        SolicitanteDto solicitanteDto = solicitanteService.SalvarSolicitante(solicitanteForm);
        return ResponseEntity.ok().body(solicitanteDto);
    }

    @ApiOperation(value = "Atualizar um Solicitante pelo id") //Anotação para descrever o endpoint
    @PutMapping("/{id}")
    public ResponseEntity<SolicitanteDto> update(@Valid @RequestBody SolicitanteForm solicitanteForm
            , @PathVariable("id") int id, BindingResult br) {
        if (br.hasErrors())
            throw new ConstraintException(br.getAllErrors().get(0).getDefaultMessage());

        SolicitanteDto solicitanteDto = solicitanteService.AtualizarSolicitante(solicitanteForm, id);
        return ResponseEntity.ok().body(solicitanteDto);
    }

    @ApiOperation(value = "Excluir um solicitante pelo id") //Anotação para descrever o endpoint
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") int id) {
        solicitanteService.RemoverSolicitante(id);
        return ResponseEntity.noContent().build();
    }
}

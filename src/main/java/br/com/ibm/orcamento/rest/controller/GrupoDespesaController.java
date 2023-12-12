package br.com.ibm.orcamento.rest.controller;

import br.com.ibm.orcamento.rest.dto.GrupoDespesaDto;
import br.com.ibm.orcamento.rest.form.GrupoDespesaForm;
import br.com.ibm.orcamento.service.GrupoDespesaService;
import br.com.ibm.orcamento.service.exceptions.ConstraintException;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/grupoDespesa")
public class GrupoDespesaController {
    @Autowired
    private GrupoDespesaService grupoDespesaService;

    @ApiOperation(value = "Retorna uma lista de Grupo Despesa")
    @GetMapping
    public ResponseEntity<List<GrupoDespesaDto>> findAll() {
        List<GrupoDespesaDto> grupoDespesaDtoList = grupoDespesaService.ObterTodos();
        return ResponseEntity.ok().body(grupoDespesaDtoList);
    }

    @ApiOperation(value = "Retorna um Grupo Despesa pelo id")
    @GetMapping("/{id}")
    public ResponseEntity<GrupoDespesaDto> find(@PathVariable("id") int id) {
        GrupoDespesaDto grupoDespesaDto = grupoDespesaService.ObterPorId(id);
        return ResponseEntity.ok().body(grupoDespesaDto);
    }

    @ApiOperation(value = "Inserir um Grupo Despesa")
    @PostMapping
    public ResponseEntity<GrupoDespesaDto> insert(@Valid @RequestBody GrupoDespesaForm grupoDespesaForm, BindingResult br) {
        if (br.hasErrors())
            throw new ConstraintException(br.getAllErrors().get(0).getDefaultMessage());

        GrupoDespesaDto grupoDespesaDto = grupoDespesaService.SalvarGrupoDespesa(grupoDespesaForm);
        return ResponseEntity.ok().body(grupoDespesaDto);
    }

    @ApiOperation(value = "Atualizar um Grupo Despesa pelo id")
    @PutMapping("/{id}")
    public ResponseEntity<GrupoDespesaDto> update(@Valid @RequestBody GrupoDespesaForm grupoDespesaForm
            , @PathVariable("id") int id, BindingResult br) {
        if (br.hasErrors())
            throw new ConstraintException(br.getAllErrors().get(0).getDefaultMessage());

        GrupoDespesaDto grupoDespesaDto = grupoDespesaService.AtualizarGrupoDespesa(grupoDespesaForm, id);
        return ResponseEntity.ok().body(grupoDespesaDto);
    }

    @ApiOperation(value = "Excluir um Grupo Despesa pelo id")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") int id) {
        grupoDespesaService.RemoverGrupoDespesa(id);
        return ResponseEntity.noContent().build();
    }
}

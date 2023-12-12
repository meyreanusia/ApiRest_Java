package br.com.ibm.orcamento.rest.controller;

import br.com.ibm.orcamento.rest.dto.ElementoDespesaDto;
import br.com.ibm.orcamento.rest.form.ElementoDespesaForm;
import br.com.ibm.orcamento.service.ElementoDespesaService;
import br.com.ibm.orcamento.service.exceptions.ConstraintException;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/elementoDespesa")
public class ElementoDespesaController {
    @Autowired
    private ElementoDespesaService elementoDespesaService;

    @ApiOperation(value = "Retorna uma lista de Elemento Despesa")
    @GetMapping
    public ResponseEntity<List<ElementoDespesaDto>> findAll() {
        List<ElementoDespesaDto> elementoDespesaDtoList = elementoDespesaService.ObterTodos();
        return ResponseEntity.ok().body(elementoDespesaDtoList);
    }

    @ApiOperation(value = "Retorna um Elemento Despesa pelo id")
    @GetMapping("/{id}")
    public ResponseEntity<ElementoDespesaDto> find(@PathVariable("id") int id) {
        ElementoDespesaDto elementoDespesaDto = elementoDespesaService.ObterPorId(id);
        return ResponseEntity.ok().body(elementoDespesaDto);
    }

    @ApiOperation(value = "Inserir um Elemento Despesa")
    @PostMapping
    public ResponseEntity<ElementoDespesaDto> insert(@Valid @RequestBody ElementoDespesaForm elementoDespesaForm, BindingResult br) {
        if (br.hasErrors())
            throw new ConstraintException(br.getAllErrors().get(0).getDefaultMessage());

        ElementoDespesaDto elementoDespesaDto = elementoDespesaService.SalvarElementoDespesa(elementoDespesaForm);
        return ResponseEntity.ok().body(elementoDespesaDto);
    }

    @ApiOperation(value = "Atualizar um Elemento Despesa pelo id")
    @PutMapping("/{id}")
    public ResponseEntity<ElementoDespesaDto> update(@Valid @RequestBody ElementoDespesaForm elementoDespesaForm
            , @PathVariable("id") int id, BindingResult br) {
        if (br.hasErrors())
            throw new ConstraintException(br.getAllErrors().get(0).getDefaultMessage());

        ElementoDespesaDto elementoDespesaDto = elementoDespesaService.AtualizarElementoDespesa(elementoDespesaForm, id);
        return ResponseEntity.ok().body(elementoDespesaDto);
    }

    @ApiOperation(value = "Excluir um Elemento Despesa pelo id")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") int id) {
        elementoDespesaService.RemoverElementoDespesa(id);
        return ResponseEntity.noContent().build();
    }
}

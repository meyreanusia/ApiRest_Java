package br.com.ibm.orcamento.rest.controller;

import br.com.ibm.orcamento.rest.dto.AcaoDto;
import br.com.ibm.orcamento.rest.form.AcaoForm;
import br.com.ibm.orcamento.service.AcaoService;
import br.com.ibm.orcamento.service.exceptions.ConstraintException;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/acao")
public class AcaoController {


    @Autowired
    private AcaoService acaoService;

    @ApiOperation(value = "Retorna uma lista de Ação")
    @GetMapping
    public ResponseEntity<List<AcaoDto>> findAll() {
        List<AcaoDto> acaoDtoList = acaoService.ObterTodos();
        return ResponseEntity.ok().body(acaoDtoList);
    }

    @ApiOperation(value = "Retorna uma Ação pelo id")
    @GetMapping("/{id}")
    public ResponseEntity<AcaoDto> find(@PathVariable("id") int id) {
        AcaoDto acaoDto = acaoService.ObterPorId(id);
        return ResponseEntity.ok().body(acaoDto);
    }

    @ApiOperation(value = "Inserir uma Ação")
    @PostMapping
    public ResponseEntity<AcaoDto> insert(@Valid @RequestBody AcaoForm acaoForm, BindingResult br) {
        if (br.hasErrors())
            throw new ConstraintException(br.getAllErrors().get(0).getDefaultMessage());

        AcaoDto acaoDto = acaoService.salvarAcao(acaoForm);
        return ResponseEntity.ok().body(acaoDto);
    }

    @ApiOperation(value = "Atualizar uma Ação pelo id")
    @PutMapping("/{id}")
    public ResponseEntity<AcaoDto> update(@Valid @RequestBody AcaoForm acaoForm
            , @PathVariable("id") int id, BindingResult br) {
        if (br.hasErrors())
            throw new ConstraintException(br.getAllErrors().get(0).getDefaultMessage());

        AcaoDto acaoDto = acaoService.AtualizarAcao( acaoForm, id);
        return ResponseEntity.ok().body(acaoDto);
    }

    @ApiOperation(value = "Excluir uma Ação pelo id")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") int id) {
        acaoService.RemoverAcao(id);
        return ResponseEntity.noContent().build();
    }
}

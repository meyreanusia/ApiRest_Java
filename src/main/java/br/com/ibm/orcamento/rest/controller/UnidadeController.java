package br.com.ibm.orcamento.rest.controller;

import br.com.ibm.orcamento.rest.dto.UnidadeDto;
import br.com.ibm.orcamento.rest.form.UnidadeForm;
import br.com.ibm.orcamento.service.UnidadeService;
import br.com.ibm.orcamento.service.exceptions.ConstraintException;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/unidade")
public class UnidadeController {

    @Autowired
    private UnidadeService unidadeService;

    @ApiOperation(value = "Retorna uma lista de Unidades") //Anotação para descrever o endpoint
    @GetMapping
    public ResponseEntity<List<UnidadeDto>> findAll() {
        List<UnidadeDto> unidadeDtoList = unidadeService.ObterTodos();
        return ResponseEntity.ok().body(unidadeDtoList);
    }

    @ApiOperation(value = "Retorna uma Unidade pelo id") //Anotação para descrever o endpoint
    @GetMapping("/{id}")
    public ResponseEntity<UnidadeDto> find(@PathVariable("id") int id) {
        UnidadeDto unidadeDto = unidadeService.ObterPorId(id);
        return ResponseEntity.ok().body(unidadeDto);
    }

    @ApiOperation(value = "Inserir uma Unidade") //Anotação para descrever o endpoint
    @PostMapping
    public ResponseEntity<UnidadeDto> insert(@Valid @RequestBody UnidadeForm unidadeForm, BindingResult br) {
        if (br.hasErrors())
            throw new ConstraintException(br.getAllErrors().get(0).getDefaultMessage());

        UnidadeDto unidadeDto = unidadeService.SalvarUnidade(unidadeForm);
        return ResponseEntity.ok().body(unidadeDto);
    }

    @ApiOperation(value = "Atualizar uma Unidade pelo id") //Anotação para descrever o endpoint
    @PutMapping("/{id}")
    public ResponseEntity<UnidadeDto> update(@Valid @RequestBody UnidadeForm unidadeForm
            , @PathVariable("id") int id, BindingResult br) {
        if (br.hasErrors())
            throw new ConstraintException(br.getAllErrors().get(0).getDefaultMessage());

        UnidadeDto unidadeDto = unidadeService.AtualizarUnidade(unidadeForm, id);
        return ResponseEntity.ok().body(unidadeDto);
    }

    @ApiOperation(value = "Excluir uma Unidade pelo id") //Anotação para descrever o endpoint
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") int id) {
        unidadeService.RemoverUnidade(id);
        return ResponseEntity.noContent().build();
    }
}

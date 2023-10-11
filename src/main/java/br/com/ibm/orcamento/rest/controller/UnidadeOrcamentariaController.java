package br.com.ibm.orcamento.rest.controller;

import br.com.ibm.orcamento.rest.dto.UnidadeOrcamentariaDto;
import br.com.ibm.orcamento.rest.form.UnidadeOrcamentariaForm;
import br.com.ibm.orcamento.service.UnidadeOrcamentariaService;
import br.com.ibm.orcamento.service.exceptions.ConstraintException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/unidadeorcamentaria")
public class UnidadeOrcamentariaController {
    @Autowired
    private UnidadeOrcamentariaService unidadeOrcamentariaService;

    @GetMapping
    public ResponseEntity<List<UnidadeOrcamentariaDto>> findAll() {
        List<UnidadeOrcamentariaDto> unidadeOrcamentariaDtoList = unidadeOrcamentariaService.ObterTodos();
        return ResponseEntity.ok().body(unidadeOrcamentariaDtoList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UnidadeOrcamentariaDto> find(@PathVariable("id") int id) {
        UnidadeOrcamentariaDto unidadeOrcamentariaDto = unidadeOrcamentariaService.ObterPorId(id);
        return ResponseEntity.ok().body(unidadeOrcamentariaDto);
    }

    @PostMapping
    public ResponseEntity<UnidadeOrcamentariaDto> insert(@Valid @RequestBody UnidadeOrcamentariaForm unidadeOrcamentariaForm, BindingResult br) {
        if (br.hasErrors())
            throw new ConstraintException(br.getAllErrors().get(0).getDefaultMessage());

        UnidadeOrcamentariaDto unidadeOrcamentariaDto = unidadeOrcamentariaService.SalvarUnidadeOrcamentaria(unidadeOrcamentariaForm);
        return ResponseEntity.ok().body(unidadeOrcamentariaDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UnidadeOrcamentariaDto> update(@Valid @RequestBody UnidadeOrcamentariaForm unidadeOrcamentariaForm
            , @PathVariable("id") int id, BindingResult br) {
        if (br.hasErrors())
            throw new ConstraintException(br.getAllErrors().get(0).getDefaultMessage());

        UnidadeOrcamentariaDto unidadeOrcamentariaDto = unidadeOrcamentariaService.AtualizarUnidadeOrcamentaria(unidadeOrcamentariaForm, id);
        return ResponseEntity.ok().body(unidadeOrcamentariaDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") int id) {
        unidadeOrcamentariaService.RemoverUnidadeOrcamentaria(id);
        return ResponseEntity.noContent().build();
    }
}

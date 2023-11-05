package br.com.ibm.orcamento.rest.controller;

import br.com.ibm.orcamento.rest.dto.FonteRecursoDto;
import br.com.ibm.orcamento.rest.form.FonteRecursoForm;
import br.com.ibm.orcamento.service.FonteRecursoSercice;
import br.com.ibm.orcamento.service.exceptions.ConstraintException;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/fonteRecurso")
public class FonteRecursoController {

    @Autowired
    private FonteRecursoSercice fonteRecursoSercice;

    @ApiOperation(value = "Retorna uma lista de Fonte Recurso")
    @GetMapping
    public ResponseEntity<List<FonteRecursoDto>> findAll() {
        List<FonteRecursoDto>FonteRecursoList = fonteRecursoSercice.ObterTodos();
        return ResponseEntity.ok().body(FonteRecursoList);
    }

    @ApiOperation(value = "Retorna uma Fonte Recurso pelo id")
    @GetMapping("/{id}")
    public ResponseEntity<FonteRecursoDto> find(@PathVariable("id") int id) {
        FonteRecursoDto fonteRecursoDto = fonteRecursoSercice.ObterPorId(id);
        return ResponseEntity.ok().body(fonteRecursoDto);
    }

    @ApiOperation(value = "Inserir uma Fonte Recurso")
    @PostMapping
    public ResponseEntity<FonteRecursoDto> insert(@Valid @RequestBody FonteRecursoForm fonteRecursoForm, BindingResult br) {
        if (br.hasErrors())
            throw new ConstraintException(br.getAllErrors().get(0).getDefaultMessage());

        FonteRecursoDto fonteRecursoDto = fonteRecursoSercice.salvarFonteRecurso(fonteRecursoForm);
        return ResponseEntity.ok().body(fonteRecursoDto);
    }

    @ApiOperation(value = "Atualizar uma Fonte Recurso pelo id")
    @PutMapping("/{id}")
    public ResponseEntity<FonteRecursoDto> update(@Valid @RequestBody FonteRecursoForm fonteRecursoForm
            , @PathVariable("id") int id, BindingResult br) {
        if (br.hasErrors())
            throw new ConstraintException(br.getAllErrors().get(0).getDefaultMessage());

        FonteRecursoDto fonteRecursoDto = fonteRecursoSercice.AtualizarFonteRecurso( fonteRecursoForm, id);
        return ResponseEntity.ok().body(fonteRecursoDto);
    }

    @ApiOperation(value = "Excluir uma Fonte Recurso pelo id")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") int id) {
        fonteRecursoSercice.RemoverFonteRecurso(id);
        return ResponseEntity.noContent().build();
    }
}

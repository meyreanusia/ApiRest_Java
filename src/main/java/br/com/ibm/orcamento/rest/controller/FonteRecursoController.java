package br.com.ibm.orcamento.rest.controller;

import br.com.ibm.orcamento.rest.dto.FonteRecursoDto;
import br.com.ibm.orcamento.rest.form.FonteRecursoForm;
import br.com.ibm.orcamento.service.FonteRecursoSercice;
import br.com.ibm.orcamento.service.exceptions.ConstraintException;
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

    @GetMapping
    public ResponseEntity<List<FonteRecursoDto>> findAll() {
        List<FonteRecursoDto>FonteRecursoList = fonteRecursoSercice.ObterTodos();
        return ResponseEntity.ok().body(FonteRecursoList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<FonteRecursoDto> find(@PathVariable("id") int id) {
        FonteRecursoDto fonteRecursoDto = fonteRecursoSercice.ObterPorId(id);
        return ResponseEntity.ok().body(fonteRecursoDto);
    }

    @PostMapping
    public ResponseEntity<FonteRecursoDto> insert(@Valid @RequestBody FonteRecursoForm fonteRecursoForm, BindingResult br) {
        if (br.hasErrors())
            throw new ConstraintException(br.getAllErrors().get(0).getDefaultMessage());

        FonteRecursoDto fonteRecursoDto = fonteRecursoSercice.salvarFonteRecurso(fonteRecursoForm);
        return ResponseEntity.ok().body(fonteRecursoDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<FonteRecursoDto> update(@Valid @RequestBody FonteRecursoForm fonteRecursoForm
            , @PathVariable("id") int id, BindingResult br) {
        if (br.hasErrors())
            throw new ConstraintException(br.getAllErrors().get(0).getDefaultMessage());

        FonteRecursoDto fonteRecursoDto = fonteRecursoSercice.AtualizarFonteRecurso( fonteRecursoForm, id);
        return ResponseEntity.ok().body(fonteRecursoDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") int id) {
        fonteRecursoSercice.RemoverFonteRecurso(id);
        return ResponseEntity.noContent().build();
    }
}

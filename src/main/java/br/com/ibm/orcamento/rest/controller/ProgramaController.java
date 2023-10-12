package br.com.ibm.orcamento.rest.controller;

import br.com.ibm.orcamento.rest.dto.ElementoDespesaDto;
import br.com.ibm.orcamento.rest.dto.ProgramaDto;
import br.com.ibm.orcamento.rest.form.ElementoDespesaForm;
import br.com.ibm.orcamento.rest.form.ProgramaForm;
import br.com.ibm.orcamento.service.ElementoDespesaService;
import br.com.ibm.orcamento.service.ProgramaService;
import br.com.ibm.orcamento.service.exceptions.ConstraintException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/programa")
public class ProgramaController {
    @Autowired
    private ProgramaService programaService;

    @GetMapping
    public ResponseEntity<List<ProgramaDto>> findAll() {
        List<ProgramaDto> programaList = programaService.ObterTodos();
        return ResponseEntity.ok().body(programaList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProgramaDto> find(@PathVariable("id") int id) {
        ProgramaDto programaDto = programaService.ObterPorId(id);
        return ResponseEntity.ok().body(programaDto);
    }

    @PostMapping
    public ResponseEntity<ProgramaDto> insert(@Valid @RequestBody ProgramaForm programaForm, BindingResult br) {
        if (br.hasErrors())
            throw new ConstraintException(br.getAllErrors().get(0).getDefaultMessage());

        ProgramaDto programaDto = programaService.SalvarPrograma(programaForm);
        return ResponseEntity.ok().body(programaDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProgramaDto> update(@Valid @RequestBody ProgramaForm programaForm
            , @PathVariable("id") int id, BindingResult br) {
        if (br.hasErrors())
            throw new ConstraintException(br.getAllErrors().get(0).getDefaultMessage());

        ProgramaDto programaDto = programaService.AtualizarPrograma(programaForm, id);
        return ResponseEntity.ok().body(programaDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") int id) {
        programaService.RemoverPrograma(id);
        return ResponseEntity.noContent().build();
    }
}

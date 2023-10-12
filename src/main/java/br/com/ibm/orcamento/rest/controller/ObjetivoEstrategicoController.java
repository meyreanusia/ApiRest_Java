package br.com.ibm.orcamento.rest.controller;

import br.com.ibm.orcamento.rest.dto.ObjetivoEstrategicoDto;
import br.com.ibm.orcamento.rest.form.ObjetivoEstrategicoForm;
import br.com.ibm.orcamento.service.ObjetivoEstrategicoService;
import br.com.ibm.orcamento.service.exceptions.ConstraintException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/objetivoestrategico")
public class ObjetivoEstrategicoController {
    @Autowired
    private ObjetivoEstrategicoService objetivoEstrategicoService;

    @GetMapping
    public ResponseEntity<List<ObjetivoEstrategicoDto>> findAll() {
        List<ObjetivoEstrategicoDto> objetivoEstrategicoDtoList = objetivoEstrategicoService.ObterTodos();
        return ResponseEntity.ok().body(objetivoEstrategicoDtoList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ObjetivoEstrategicoDto> find(@PathVariable("id") int id) {
        ObjetivoEstrategicoDto objetivoEstrategicoDto = objetivoEstrategicoService.ObterPorId(id);
        return ResponseEntity.ok().body(objetivoEstrategicoDto);
    }

    @PostMapping
    public ResponseEntity<ObjetivoEstrategicoDto> insert(@Valid @RequestBody ObjetivoEstrategicoForm objetivoEstrategicoForm, BindingResult br) {
        if (br.hasErrors())
            throw new ConstraintException(br.getAllErrors().get(0).getDefaultMessage());

        ObjetivoEstrategicoDto objetivoEstrategicoDto = objetivoEstrategicoService.SalvarObjetivoEstrategico(objetivoEstrategicoForm);
        return ResponseEntity.ok().body(objetivoEstrategicoDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ObjetivoEstrategicoDto> update(@Valid @RequestBody ObjetivoEstrategicoForm objetivoEstrategicoForm
            , @PathVariable("id") int id, BindingResult br) {
        if (br.hasErrors())
            throw new ConstraintException(br.getAllErrors().get(0).getDefaultMessage());

        ObjetivoEstrategicoDto objetivoEstrategicoDto = objetivoEstrategicoService.AtualizarObjetivoEstrategico(objetivoEstrategicoForm, id);
        return ResponseEntity.ok().body(objetivoEstrategicoDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") int id) {
        objetivoEstrategicoService.RemoverObjetivoEstrategico(id);
        return ResponseEntity.noContent().build();
    }
}

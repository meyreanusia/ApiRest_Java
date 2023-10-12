package br.com.ibm.orcamento.rest.controller;

import br.com.ibm.orcamento.rest.dto.ModalidadeAplicaoDto;
import br.com.ibm.orcamento.rest.form.ModalidadeAplicaoForm;
import br.com.ibm.orcamento.service.ModalidadeAplicaoService;
import br.com.ibm.orcamento.service.exceptions.ConstraintException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/modalidadeAplicacao")
public class ModalidadeAplicacaoController {
    @Autowired
    private ModalidadeAplicaoService modalidadeAplicaoService;

    @GetMapping
    public ResponseEntity<List<ModalidadeAplicaoDto>> findAll() {
        List<ModalidadeAplicaoDto>modalidadeAplicacaoList = modalidadeAplicaoService.ObterTodos();
        return ResponseEntity.ok().body(modalidadeAplicacaoList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ModalidadeAplicaoDto> find(@PathVariable("id") int id) {
        ModalidadeAplicaoDto modalidadeAplicaoDto = modalidadeAplicaoService.ObterPorId(id);
        return ResponseEntity.ok().body(modalidadeAplicaoDto);
    }

    @PostMapping
    public ResponseEntity<ModalidadeAplicaoDto> insert(@Valid @RequestBody ModalidadeAplicaoForm modalidadeAplicaoForm, BindingResult br) {
        if (br.hasErrors())
            throw new ConstraintException(br.getAllErrors().get(0).getDefaultMessage());

        ModalidadeAplicaoDto modalidadeAplicaoDto = modalidadeAplicaoService.salvarModalidadeAplicao(modalidadeAplicaoForm);
        return ResponseEntity.ok().body(modalidadeAplicaoDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ModalidadeAplicaoDto> update(@Valid @RequestBody ModalidadeAplicaoForm modalidadeAplicaoForm
            , @PathVariable("id") int id, BindingResult br) {
        if (br.hasErrors())
            throw new ConstraintException(br.getAllErrors().get(0).getDefaultMessage());

        ModalidadeAplicaoDto modalidadeAplicaoDto = modalidadeAplicaoService.AtualizarModalidadeAplicacao( modalidadeAplicaoForm, id);
        return ResponseEntity.ok().body(modalidadeAplicaoDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") int id) {
        modalidadeAplicaoService.RemoverModalidadeAplicacao(id);
        return ResponseEntity.noContent().build();
    }
}

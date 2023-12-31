package br.com.ibm.orcamento.rest.controller;

import br.com.ibm.orcamento.rest.dto.ModalidadeAplicaoDto;
import br.com.ibm.orcamento.rest.form.ModalidadeAplicaoForm;
import br.com.ibm.orcamento.service.ModalidadeAplicaoService;
import br.com.ibm.orcamento.service.exceptions.ConstraintException;
import io.swagger.annotations.ApiOperation;
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

    @ApiOperation(value = "Retorna uma lista de Modalidade Aplicação")
    @GetMapping
    public ResponseEntity<List<ModalidadeAplicaoDto>> findAll() {
        List<ModalidadeAplicaoDto>modalidadeAplicacaoList = modalidadeAplicaoService.ObterTodos();
        return ResponseEntity.ok().body(modalidadeAplicacaoList);
    }

    @ApiOperation(value = "Retorna uma Modalidade Aplicação pelo id")
    @GetMapping("/{id}")
    public ResponseEntity<ModalidadeAplicaoDto> find(@PathVariable("id") int id) {
        ModalidadeAplicaoDto modalidadeAplicaoDto = modalidadeAplicaoService.ObterPorId(id);
        return ResponseEntity.ok().body(modalidadeAplicaoDto);
    }

    @ApiOperation(value = "Inserir uma Modalidade Aplicação")
    @PostMapping
    public ResponseEntity<ModalidadeAplicaoDto> insert(@Valid @RequestBody ModalidadeAplicaoForm modalidadeAplicaoForm, BindingResult br) {
        if (br.hasErrors())
            throw new ConstraintException(br.getAllErrors().get(0).getDefaultMessage());

        ModalidadeAplicaoDto modalidadeAplicaoDto = modalidadeAplicaoService.salvarModalidadeAplicao(modalidadeAplicaoForm);
        return ResponseEntity.ok().body(modalidadeAplicaoDto);
    }

    @ApiOperation(value = "Atualizar uma Modalidade Aplicação pelo id")
    @PutMapping("/{id}")
    public ResponseEntity<ModalidadeAplicaoDto> update(@Valid @RequestBody ModalidadeAplicaoForm modalidadeAplicaoForm
            , @PathVariable("id") int id, BindingResult br) {
        if (br.hasErrors())
            throw new ConstraintException(br.getAllErrors().get(0).getDefaultMessage());

        ModalidadeAplicaoDto modalidadeAplicaoDto = modalidadeAplicaoService.AtualizarModalidadeAplicacao( modalidadeAplicaoForm, id);
        return ResponseEntity.ok().body(modalidadeAplicaoDto);
    }

    @ApiOperation(value = "Excluir uma Modalidade Aplicação pelo id")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") int id) {
        modalidadeAplicaoService.RemoverModalidadeAplicacao(id);
        return ResponseEntity.noContent().build();
    }
}

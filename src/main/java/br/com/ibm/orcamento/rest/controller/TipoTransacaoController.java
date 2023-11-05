package br.com.ibm.orcamento.rest.controller;

import br.com.ibm.orcamento.rest.dto.TipoTransacaoDto;
import br.com.ibm.orcamento.rest.form.TipoTransacaoForm;
import br.com.ibm.orcamento.service.TipoTransacaoService;
import br.com.ibm.orcamento.service.exceptions.ConstraintException;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/tipotransacao")
public class TipoTransacaoController {
    @Autowired
    private TipoTransacaoService tipoTransacaoService;

    @ApiOperation(value = "Retorna uma lista de Tipos de Transação")
    @GetMapping
    public ResponseEntity<List<TipoTransacaoDto>> findAll() {
        List<TipoTransacaoDto> tipoTransacaoDtoList = tipoTransacaoService.ObterTodos();
        return ResponseEntity.ok().body(tipoTransacaoDtoList);
    }

    @ApiOperation(value = "Retorna um Tipo de Transação pelo id")
    @GetMapping("/{id}")
    public ResponseEntity<TipoTransacaoDto> find(@PathVariable("id") int id) {
        TipoTransacaoDto tipoTransacaoDto = tipoTransacaoService.ObterPorId(id);
        return ResponseEntity.ok().body(tipoTransacaoDto);
    }

    @ApiOperation(value = "Inserir um Tipo de Transação")
    @PostMapping
    public ResponseEntity<TipoTransacaoDto> insert(@Valid @RequestBody TipoTransacaoForm tipoTransacaoForm, BindingResult br) {
        if (br.hasErrors())
            throw new ConstraintException(br.getAllErrors().get(0).getDefaultMessage());

        TipoTransacaoDto tipoTransacaoDto = tipoTransacaoService.SalvarTipoTransacao(tipoTransacaoForm);
        return ResponseEntity.ok().body(tipoTransacaoDto);
    }

    @ApiOperation(value = "Atualizar um Tipo de Transação pelo id")
    @PutMapping("/{id}")
    public ResponseEntity<TipoTransacaoDto> update(@Valid @RequestBody TipoTransacaoForm tipoTransacaoForm
            , @PathVariable("id") int id, BindingResult br) {
        if (br.hasErrors())
            throw new ConstraintException(br.getAllErrors().get(0).getDefaultMessage());

        TipoTransacaoDto tipoTransacaoDto = tipoTransacaoService.AtualizarTipoTransacao(tipoTransacaoForm, id);
        return ResponseEntity.ok().body(tipoTransacaoDto);
    }

    @ApiOperation(value = "Excluir um Tipo de Transação pelo id")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") int id) {
        tipoTransacaoService.RemoverTipoTransacao(id);
        return ResponseEntity.noContent().build();
    }
}

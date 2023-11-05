package br.com.ibm.orcamento.rest.controller;

import br.com.ibm.orcamento.rest.dto.TipoLancamentoDto;
import br.com.ibm.orcamento.rest.form.TipoLancamentoForm;
import br.com.ibm.orcamento.service.TipoLancamentoService;
import br.com.ibm.orcamento.service.exceptions.ConstraintException;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/tipoLancamento")
public class TipoLancamentoController {


    @Autowired
    private TipoLancamentoService tipoLancamentoService;

    @ApiOperation(value = "Retorna uma lista de Tipos de Lançamentos")
    @GetMapping
    public ResponseEntity<List<TipoLancamentoDto>> findAll() {
        List<TipoLancamentoDto> tipoLancamentoDtoList = tipoLancamentoService.ObterTodos();
        return ResponseEntity.ok().body(tipoLancamentoDtoList);
    }

    @ApiOperation(value = "Retorna um Tipo de Lançamento pelo id")
    @GetMapping("/{id}")
    public ResponseEntity<TipoLancamentoDto> find(@PathVariable("id") int id) {
        TipoLancamentoDto tipoLancamentoDto = tipoLancamentoService.ObterPorId(id);
        return ResponseEntity.ok().body(tipoLancamentoDto);
    }

    @ApiOperation(value = "Inserir um Tipo de Lançamento")
    @PostMapping
    public ResponseEntity<TipoLancamentoDto> insert(@Valid @RequestBody TipoLancamentoForm tipoLancamentoForm, BindingResult br) {
        if (br.hasErrors())
            throw new ConstraintException(br.getAllErrors().get(0).getDefaultMessage());

        TipoLancamentoDto tipoLancamentoDto = tipoLancamentoService.salvarTipoLancamento(tipoLancamentoForm);
        return ResponseEntity.ok().body(tipoLancamentoDto);
    }

    @ApiOperation(value = "Atualizar um Tipo de Lançamento pelo id")
    @PutMapping("/{id}")
    public ResponseEntity<TipoLancamentoDto> update(@Valid @RequestBody TipoLancamentoForm tipoLancamentoForm
            , @PathVariable("id") int id, BindingResult br) {
        if (br.hasErrors())
            throw new ConstraintException(br.getAllErrors().get(0).getDefaultMessage());

        TipoLancamentoDto tipoLancamentoDto = tipoLancamentoService.AtualizarTipoLancamento( tipoLancamentoForm, id);
        return ResponseEntity.ok().body(tipoLancamentoDto);
    }

    @ApiOperation(value = "Excluir um Tipo de Lançamento pelo id")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") int id) {
        tipoLancamentoService.RemoverTipoLancamento(id);
        return ResponseEntity.noContent().build();
    }
}

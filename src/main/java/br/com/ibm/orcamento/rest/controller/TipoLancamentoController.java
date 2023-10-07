package br.com.ibm.orcamento.rest.controller;

import br.com.ibm.orcamento.rest.dto.TipoLancamentoDto;
import br.com.ibm.orcamento.rest.dto.UnidadeDto;
import br.com.ibm.orcamento.rest.form.TipoLancamentoForm;
import br.com.ibm.orcamento.rest.form.UnidadeForm;
import br.com.ibm.orcamento.service.TipoLancamentoService;
import br.com.ibm.orcamento.service.UnidadeService;
import br.com.ibm.orcamento.service.exceptions.ConstraintException;
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

    @GetMapping
    public ResponseEntity<List<TipoLancamentoDto>> findAll() {
        List<TipoLancamentoDto> tipoLancamentoDtoList = tipoLancamentoService.ObterTodos();
        return ResponseEntity.ok().body(tipoLancamentoDtoList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TipoLancamentoDto> find(@PathVariable("id") int id) {
        TipoLancamentoDto tipoLancamentoDto = tipoLancamentoService.ObterPorId(id);
        return ResponseEntity.ok().body(tipoLancamentoDto);
    }

    @PostMapping
    public ResponseEntity<TipoLancamentoDto> insert(@Valid @RequestBody TipoLancamentoForm tipoLancamentoForm, BindingResult br) {
        if (br.hasErrors())
            throw new ConstraintException(br.getAllErrors().get(0).getDefaultMessage());

        TipoLancamentoDto tipoLancamentoDto = tipoLancamentoService.salvarTipoLancamento(tipoLancamentoForm);
        return ResponseEntity.ok().body(tipoLancamentoDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TipoLancamentoDto> update(@Valid @RequestBody TipoLancamentoForm tipoLancamentoForm
            , @PathVariable("id") int id, BindingResult br) {
        if (br.hasErrors())
            throw new ConstraintException(br.getAllErrors().get(0).getDefaultMessage());

        TipoLancamentoDto tipoLancamentoDto = tipoLancamentoService.AtualizarTipoLancamento( tipoLancamentoForm, id);
        return ResponseEntity.ok().body(tipoLancamentoDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") int id) {
        tipoLancamentoService.RemoverTipoLancamento(id);
        return ResponseEntity.noContent().build();
    }
}

package br.com.ibm.orcamento.service;

import br.com.ibm.orcamento.model.TipoLancamentoModel;
import br.com.ibm.orcamento.model.UnidadeModel;
import br.com.ibm.orcamento.repository.TipoLancamentoRepository;
import br.com.ibm.orcamento.rest.dto.TipoLancamentoDto;
import br.com.ibm.orcamento.rest.dto.UnidadeDto;
import br.com.ibm.orcamento.rest.form.TipoLancamentoForm;
import br.com.ibm.orcamento.rest.form.UnidadeForm;
import br.com.ibm.orcamento.service.exceptions.BusinessRuleException;
import br.com.ibm.orcamento.service.exceptions.DataIntegrityException;
import br.com.ibm.orcamento.service.exceptions.ObjectNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TipoLancamentoService {

    @Autowired
    private TipoLancamentoRepository tipoLancamentoRepository;

    @Autowired
    private ModelMapper modelMapper;

    public TipoLancamentoDto ObterPorId(int id) {
        try {
            TipoLancamentoModel tipoLancamentoModel = tipoLancamentoRepository.findById(id).get();
            return modelMapper.map(tipoLancamentoModel, TipoLancamentoDto.class);

        } catch (NoSuchElementException e) {
            throw new ObjectNotFoundException("Tipo laçamento não encontrado! Código : " + id + ", Tipo: " + TipoLancamentoModel.class.getName());
        }
    }

    public List<TipoLancamentoDto> ObterTodos(){
        try {
            List<TipoLancamentoModel> tipoLancamentoList = tipoLancamentoRepository.findAll();

            return tipoLancamentoList.stream()
                    .map(tipoLancamento -> modelMapper.map(tipoLancamento, TipoLancamentoDto.class))
                    .collect(Collectors.toList());
        } catch (BusinessRuleException e) {
            throw new BusinessRuleException("Não é possível consultar os Tipos de Lançamentos!");
        }
    }

    public TipoLancamentoDto salvarTipoLancamento(TipoLancamentoForm tipoLancamentoForm) {
        try {
            TipoLancamentoModel tipoLancamentoNovo = modelMapper.map(tipoLancamentoForm, TipoLancamentoModel.class);
            tipoLancamentoNovo.setDataCadastro(LocalDateTime.now());

            tipoLancamentoNovo = tipoLancamentoRepository.save(tipoLancamentoNovo);

            return modelMapper.map(tipoLancamentoNovo, TipoLancamentoDto.class);

        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException("Campo(s) obrigatório(s) do Tipo Lançamento não foi(foram) preenchido(s).");
        }
    }

    public TipoLancamentoDto AtualizarTipoLancamento(TipoLancamentoForm tipoLancamentoForm, int id) {
        try {
            Optional<TipoLancamentoModel> tipoLancamentoExistente = tipoLancamentoRepository.findById(id);

            if (tipoLancamentoExistente.isPresent()) {
                TipoLancamentoModel tipoLancamentoAtualizado = tipoLancamentoExistente.get();
                tipoLancamentoAtualizado.setNome(tipoLancamentoForm.getNome());
                tipoLancamentoAtualizado.setDataAlteracao(LocalDateTime.now());
                tipoLancamentoAtualizado = tipoLancamentoRepository.save(tipoLancamentoAtualizado);

                return modelMapper.map(tipoLancamentoAtualizado, TipoLancamentoDto.class);
            }else{
                throw new DataIntegrityException("O Código do Tipo lançamento não existe na base de dados!");
            }
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException("Campo(s) obrigatório(s) do Tipo de Lançamento não foi(foram) preenchido(s).");
        }
    }

    public void RemoverTipoLancamento(int id) {
        try {
            if (tipoLancamentoRepository.existsById(id)) {
                tipoLancamentoRepository.deleteById(id);

            }else {
                throw new DataIntegrityException("O código do Tipo de Lançamento não existe na base de dados!");
            }
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException("Não é possível excluir um Tipo de Lançamento!");
        }
    }

}

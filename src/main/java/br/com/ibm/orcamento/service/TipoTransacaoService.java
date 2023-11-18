package br.com.ibm.orcamento.service;

import br.com.ibm.orcamento.model.TipoTransacaoModel;
import br.com.ibm.orcamento.repository.TipoTransacaoRepository;
import br.com.ibm.orcamento.rest.dto.TipoTransacaoDto;
import br.com.ibm.orcamento.rest.form.TipoTransacaoForm;
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
public class TipoTransacaoService {

    @Autowired
    private TipoTransacaoRepository tipoTransacaoRepository;

    @Autowired
    private ModelMapper modelMapper;

    public TipoTransacaoDto ObterPorId(int id) {
        try {
            TipoTransacaoModel tipoTransacaoModel = tipoTransacaoRepository.findById(id).get();

            return modelMapper.map(tipoTransacaoModel, TipoTransacaoDto.class);

        } catch (NoSuchElementException e) {
            throw new ObjectNotFoundException("Tipo Transação não encontrado! Código : " + id + ", Tipo: " + TipoTransacaoModel.class.getName());
        }
    }

    public List<TipoTransacaoDto> ObterTodos(){
        try {
            List<TipoTransacaoModel> tipoTransacaoList = tipoTransacaoRepository.findAll();

            return tipoTransacaoList.stream()
                    .map(tipoTransacao -> modelMapper.map(tipoTransacao, TipoTransacaoDto.class))
                    .collect(Collectors.toList());
        } catch (BusinessRuleException e) {
            throw new BusinessRuleException("Não é possível consultar os Tipos de Transação!");
        }
    }

    public TipoTransacaoDto SalvarTipoTransacao(TipoTransacaoForm tipoTransacaoForm) {
        try {
            TipoTransacaoModel tipoTransacaoNovo = modelMapper.map(tipoTransacaoForm, TipoTransacaoModel.class);

            tipoTransacaoNovo = tipoTransacaoRepository.save(tipoTransacaoNovo);

            return modelMapper.map(tipoTransacaoNovo, TipoTransacaoDto.class);

        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException("Campo(s) obrigatório(s) do Tipo Transação não foi(foram) preenchido(s).");
        }
    }

    public TipoTransacaoDto AtualizarTipoTransacao(TipoTransacaoForm tipoTransacaoForm, int id) {
        try {
            Optional<TipoTransacaoModel> tipoTransacaoExistente = tipoTransacaoRepository.findById(id);

            if (tipoTransacaoExistente.isPresent()) {
                TipoTransacaoModel tipoTransacaoAtualizado = tipoTransacaoExistente.get();
                tipoTransacaoAtualizado.setNome(tipoTransacaoForm.getNome());
                tipoTransacaoAtualizado = tipoTransacaoRepository.save(tipoTransacaoAtualizado);

                return modelMapper.map(tipoTransacaoAtualizado, TipoTransacaoDto.class);
            }else{
                throw new DataIntegrityException("O Código do Tipo Transação não existe na base de dados!");
            }
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException("Campo(s) obrigatório(s) do Tipo Transação não foi(foram) preenchido(s).");
        }
    }

    public void RemoverTipoTransacao(int id) {
        try {
            if (tipoTransacaoRepository.existsById(id)) {
                tipoTransacaoRepository.deleteById(id);

            }else {
                throw new DataIntegrityException("O código do Tipo Transação não existe na base de dados!");
            }
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException("Não é possível excluir um Tipo Transação!");
        }
    }
}

package br.com.ibm.orcamento.service;

import br.com.ibm.orcamento.model.ModalidadeAplicacaoModel;
import br.com.ibm.orcamento.repository.ModalidadeAplicacaoRepository;
import br.com.ibm.orcamento.rest.dto.ModalidadeAplicaoDto;
import br.com.ibm.orcamento.rest.form.ModalidadeAplicaoForm;
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
public class ModalidadeAplicaoService {

    @Autowired
    private ModalidadeAplicacaoRepository modalidadeAplicacaoRepository;

    @Autowired
    private ModelMapper modelMapper;

    public ModalidadeAplicaoDto ObterPorId(int id) {
        try {
            ModalidadeAplicacaoModel modalidadeAplicacaoModel = modalidadeAplicacaoRepository.findById(id).get();
            return modelMapper.map(modalidadeAplicacaoModel, ModalidadeAplicaoDto.class);

        } catch (NoSuchElementException e) {
            throw new ObjectNotFoundException("Modalidade Aplicacao não encontrado! Código : " + id + ", Tipo: " + ModalidadeAplicacaoModel.class.getName());
        }
    }

    public List<ModalidadeAplicaoDto> ObterTodos(){
        try {
            List<ModalidadeAplicacaoModel> modalidadeAplicacaoModelsList = modalidadeAplicacaoRepository.findAll();

            return modalidadeAplicacaoModelsList.stream()
                    .map(modalidadeAplicao -> modelMapper.map(modalidadeAplicao, ModalidadeAplicaoDto.class))
                    .collect(Collectors.toList());
        } catch (BusinessRuleException e) {
            throw new BusinessRuleException("Não é possível consultar as Modalidades plicação!");
        }
    }

    public ModalidadeAplicaoDto salvarModalidadeAplicao(ModalidadeAplicaoForm modalidadeAplicaoForm) {
        try {
            ModalidadeAplicacaoModel modalidadeAplicacaoModelNovo = modelMapper.map(modalidadeAplicaoForm, ModalidadeAplicacaoModel.class);
            modalidadeAplicacaoModelNovo.setDataCadastro(LocalDateTime.now());

            modalidadeAplicacaoModelNovo = modalidadeAplicacaoRepository.save(modalidadeAplicacaoModelNovo);

            return modelMapper.map(modalidadeAplicacaoModelNovo, ModalidadeAplicaoDto.class);

        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException("Campo(s) obrigatório(s) da Modalidade Aplicação não foi(foram) preenchido(s).");
        }
    }

    public ModalidadeAplicaoDto AtualizarModalidadeAplicacao(ModalidadeAplicaoForm modalidadeAplicaoForm, int id) {
        try {
            Optional<ModalidadeAplicacaoModel> modalidadeAplicacaoModelExistente = modalidadeAplicacaoRepository.findById(id);

            if (modalidadeAplicacaoModelExistente.isPresent()) {
                ModalidadeAplicacaoModel modalidadeAplicacaoAtualizado = modalidadeAplicacaoModelExistente.get();
                modalidadeAplicacaoAtualizado.setNome(modalidadeAplicaoForm.getNome());
                modalidadeAplicacaoAtualizado.setDataAlteracao(LocalDateTime.now());
                modalidadeAplicacaoAtualizado = modalidadeAplicacaoRepository.save(modalidadeAplicacaoAtualizado);

                return modelMapper.map(modalidadeAplicacaoAtualizado, ModalidadeAplicaoDto.class);
            }else{
                throw new DataIntegrityException("O Código da Modalidade Aplicação não existe na base de dados!");
            }
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException("Campo(s) obrigatório(s) da Modalidade Aplicação não foi(foram) preenchido(s).");
        }
    }

    public void RemoverModalidadeAplicacao(int id) {
        try {
            if (modalidadeAplicacaoRepository.existsById(id)) {
                modalidadeAplicacaoRepository.deleteById(id);

            }else {
                throw new DataIntegrityException("O código do Tipo da Modalidade Aplicação não existe na base de dados!");
            }
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException("Não é possível excluir uma Modalidade Aplicação!");
        }
    }

}

package br.com.ibm.orcamento.service;

import br.com.ibm.orcamento.model.AcaoModel;
import br.com.ibm.orcamento.repository.AcaoRepository;
import br.com.ibm.orcamento.rest.dto.AcaoDto;
import br.com.ibm.orcamento.rest.form.AcaoForm;
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
public class AcaoService {
    @Autowired
    private AcaoRepository acaoRepository;

    @Autowired
    private ModelMapper modelMapper;

    public List<AcaoDto> ObterTodos(){
        try {
            List<AcaoModel> acaoModelList = acaoRepository.findAll();

            return acaoModelList.stream()
                    .map(acao -> modelMapper.map(acao, AcaoDto.class))
                    .collect(Collectors.toList());
        } catch (BusinessRuleException e) {
            throw new BusinessRuleException("Não é possível consultar as Ações!");
        }
    }
    public AcaoDto ObterPorId(int id){
        try {
            AcaoModel acaoModel = acaoRepository.findById(id).get();
            return modelMapper.map(acaoModel, AcaoDto.class);
        } catch (NoSuchElementException e){
            throw new ObjectNotFoundException("Ação não encontrado! Codigo: " + id + ", Tipo: " + AcaoModel.class.getName());
        }
    }

    public AcaoDto salvarAcao(AcaoForm acaoForm) {
        try {
            AcaoModel acaolNovo = modelMapper.map(acaoForm, AcaoModel.class);

            acaolNovo = acaoRepository.save(acaolNovo);

            return modelMapper.map(acaolNovo, AcaoDto.class);

        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException("Campo(s) obrigatório(s) da(s) Ação(ões) não foi(foram) preenchido(s).");
        }
    }


    public AcaoDto AtualizarAcao(AcaoForm acaoForm, int id) {
        try {
            Optional<AcaoModel> acaoExistente = acaoRepository.findById(id);

            if (acaoExistente.isPresent()) {
                AcaoModel acaoAtualizado = acaoExistente.get();
                acaoAtualizado.setNome(acaoForm.getNome());
                acaoAtualizado = acaoRepository.save(acaoAtualizado);

                return modelMapper.map(acaoAtualizado, AcaoDto.class);
            }else{
                throw new DataIntegrityException("O Código da Ação não existe na base de dados!");
            }
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException("Campo(s) obrigatório(s) da Ação  não foi(foram) preenchido(s).");
        }
    }

    public void RemoverAcao(int id) {
        try {
            if (acaoRepository.existsById(id)) {
                acaoRepository.deleteById(id);

            }else {
                throw new DataIntegrityException("O código da Ação não existe na base de dados!");
            }
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException("Não é possível excluir uma Ação!");
        }
    }
}

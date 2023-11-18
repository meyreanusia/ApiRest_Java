package br.com.ibm.orcamento.service;

import br.com.ibm.orcamento.model.UnidadeOrcamentariaModel;
import br.com.ibm.orcamento.repository.UnidadeOrcamentariaRepository;
import br.com.ibm.orcamento.rest.dto.UnidadeOrcamentariaDto;
import br.com.ibm.orcamento.rest.form.UnidadeOrcamentariaForm;
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
public class UnidadeOrcamentariaService {
    @Autowired
    private UnidadeOrcamentariaRepository unidadeOrcamentariaRepository;

    @Autowired
    private ModelMapper modelMapper;

    public UnidadeOrcamentariaDto ObterPorId(int id) {
        try {
            UnidadeOrcamentariaModel unidadeOrcamentariaModel = unidadeOrcamentariaRepository.findById(id).get();

            return modelMapper.map(unidadeOrcamentariaModel, UnidadeOrcamentariaDto.class);

        } catch (NoSuchElementException e) {
            throw new ObjectNotFoundException("Unidade Orcamentaria não encontrado! Código : " + id + ", Tipo: " + UnidadeOrcamentariaModel.class.getName());
        }
    }

    public List<UnidadeOrcamentariaDto> ObterTodos(){
        try {
            List<UnidadeOrcamentariaModel> unidadeOrcamentariaList = unidadeOrcamentariaRepository.findAll();

            return unidadeOrcamentariaList.stream()
                    .map(unidadeOrcamentaria -> modelMapper.map(unidadeOrcamentaria, UnidadeOrcamentariaDto.class))
                    .collect(Collectors.toList());
        } catch (BusinessRuleException e) {
            throw new BusinessRuleException("Não é possível consultar as Unidades Orcamentarias!");
        }
    }

    public UnidadeOrcamentariaDto SalvarUnidadeOrcamentaria(UnidadeOrcamentariaForm unidadeOrcamentariaForm) {
        try {
            UnidadeOrcamentariaModel unidadeOrcamentariaNova = modelMapper.map(unidadeOrcamentariaForm, UnidadeOrcamentariaModel.class);

            unidadeOrcamentariaNova = unidadeOrcamentariaRepository.save(unidadeOrcamentariaNova);

            return modelMapper.map(unidadeOrcamentariaNova, UnidadeOrcamentariaDto.class);

        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException("Campo(s) obrigatório(s) da Unidade Orcamentaria não foi(foram) preenchido(s).");
        }
    }

    public UnidadeOrcamentariaDto AtualizarUnidadeOrcamentaria(UnidadeOrcamentariaForm unidadeOrcamentariaForm, int id) {
        try {
            Optional<UnidadeOrcamentariaModel> unidadeOrcamentariaExistente = unidadeOrcamentariaRepository.findById(id);

            if (unidadeOrcamentariaExistente.isPresent()) {
                UnidadeOrcamentariaModel unidadeOrcamentariaAtualizada = unidadeOrcamentariaExistente.get();
                unidadeOrcamentariaAtualizada.setNome(unidadeOrcamentariaForm.getNome());
                unidadeOrcamentariaAtualizada.setCodigo(unidadeOrcamentariaForm.getCodigo()); // Adicione esta linha para atualizar o código
                unidadeOrcamentariaAtualizada = unidadeOrcamentariaRepository.save(unidadeOrcamentariaAtualizada);

                return modelMapper.map(unidadeOrcamentariaAtualizada, UnidadeOrcamentariaDto.class);
            } else {
                throw new DataIntegrityException("O Código da Unidade Orcamentaria não existe na base de dados!");
            }
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException("Campo(s) obrigatório(s) da Unidade Orcamentaria não foi(foram) preenchido(s).");
        }
    }


    public void RemoverUnidadeOrcamentaria(int id) {
        try {
            if (unidadeOrcamentariaRepository.existsById(id)) {
                unidadeOrcamentariaRepository.deleteById(id);

            }else {
                throw new DataIntegrityException("O código da Unidade Orcamentaria não existe na base de dados!");
            }
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException("Não é possível excluir a Unidade Orcamentaria!");
        }
    }
}

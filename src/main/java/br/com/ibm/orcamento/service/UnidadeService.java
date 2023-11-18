package br.com.ibm.orcamento.service;

import br.com.ibm.orcamento.model.UnidadeModel;
import br.com.ibm.orcamento.repository.UnidadeRepository;
import br.com.ibm.orcamento.rest.dto.UnidadeDto;
import br.com.ibm.orcamento.rest.form.UnidadeForm;
import br.com.ibm.orcamento.service.exceptions.BusinessRuleException;
import br.com.ibm.orcamento.service.exceptions.DataIntegrityException;
import br.com.ibm.orcamento.service.exceptions.ObjectNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UnidadeService {

    @Autowired
    private UnidadeRepository unidadeRepository;

    @Autowired
    private ModelMapper modelMapper;

    public UnidadeDto ObterPorId(int id) {
        try {
            UnidadeModel unidadeModel = unidadeRepository.findById(id).get();

            return modelMapper.map(unidadeModel, UnidadeDto.class);

        } catch (NoSuchElementException e) {
            throw new ObjectNotFoundException("Unidade não encontrada! Código : " + id + ", Tipo: " + UnidadeModel.class.getName());
        }
    }

    public List<UnidadeDto> ObterTodos(){
        try {
            List<UnidadeModel> unidadeList = unidadeRepository.findAll();

            return unidadeList.stream()
                    .map(unidade -> modelMapper.map(unidade, UnidadeDto.class))
                    .collect(Collectors.toList());
        } catch (BusinessRuleException e) {
            throw new BusinessRuleException("Não é possível consultar as Unidades!");
        }
    }

    public UnidadeDto SalvarUnidade(UnidadeForm unidadeForm) {
        try {
            UnidadeModel unidadeNova = modelMapper.map(unidadeForm, UnidadeModel.class);

            unidadeNova = unidadeRepository.save(unidadeNova);

            return modelMapper.map(unidadeNova, UnidadeDto.class);

        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException("Campo(s) obrigatório(s) da Unidade não foi(foram) preenchido(s).");
        }
    }

    public UnidadeDto AtualizarUnidade(UnidadeForm unidadeForm, int id) {
        try {
            Optional<UnidadeModel> unidadeExistente = unidadeRepository.findById(id);

            if (unidadeExistente.isPresent()) {
                UnidadeModel unidadeAtualizada = unidadeExistente.get();
                unidadeAtualizada.setNome(unidadeForm.getNome());
                unidadeAtualizada = unidadeRepository.save(unidadeAtualizada);

                return modelMapper.map(unidadeAtualizada, UnidadeDto.class);
            }else{
                throw new DataIntegrityException("O Código da Unidade não existe na base de dados!");
            }
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException("Campo(s) obrigatório(s) da Unidade não foi(foram) preenchido(s).");
        }
    }

    public void RemoverUnidade(int id) {
        try {
            if (unidadeRepository.existsById(id)) {
                unidadeRepository.deleteById(id);

            }else {
                throw new DataIntegrityException("O código da Unidade não existe na base de dados!");
            }
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException("Não é possível excluir uma Unidade!");
        }
    }
}

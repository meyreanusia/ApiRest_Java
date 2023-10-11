package br.com.ibm.orcamento.service;

import br.com.ibm.orcamento.model.ElementoDespesaModel;
import br.com.ibm.orcamento.repository.ElementoDespesaRepository;
import br.com.ibm.orcamento.rest.dto.ElementoDespesaDto;
import br.com.ibm.orcamento.rest.form.ElementoDespesaForm;
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
public class ElementoDespesaService {
    @Autowired
    private ElementoDespesaRepository elementoDespesaRepository;

    @Autowired
    private ModelMapper modelMapper;

    public ElementoDespesaDto ObterPorId(int id) {
        try {
            ElementoDespesaModel elementoDespesaModel = elementoDespesaRepository.findById(id).get();

            return modelMapper.map(elementoDespesaModel, ElementoDespesaDto.class);

        } catch (NoSuchElementException e) {
            throw new ObjectNotFoundException("Elemento Despesa não encontrado! Código : " + id + ", Tipo: " + ElementoDespesaModel.class.getName());
        }
    }

    public List<ElementoDespesaDto> ObterTodos(){
        try {
            List<ElementoDespesaModel> elementoDespesaList = elementoDespesaRepository.findAll();

            return elementoDespesaList.stream()
                    .map(elementoDespesa -> modelMapper.map(elementoDespesa, ElementoDespesaDto.class))
                    .collect(Collectors.toList());
        } catch (BusinessRuleException e) {
            throw new BusinessRuleException("Não é possível consultar os Elementos Despesa!");
        }
    }

    public ElementoDespesaDto SalvarElementoDespesa(ElementoDespesaForm elementoDespesaForm) {
        try {
            ElementoDespesaModel elementoDespesaNovo = modelMapper.map(elementoDespesaForm, ElementoDespesaModel.class);
            elementoDespesaNovo.setDataCadastro(LocalDateTime.now());

            elementoDespesaNovo = elementoDespesaRepository.save(elementoDespesaNovo);

            return modelMapper.map(elementoDespesaNovo, ElementoDespesaDto.class);

        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException("Campo(s) obrigatório(s) do Elemento Despesa não foi(foram) preenchido(s).");
        }
    }

    public ElementoDespesaDto AtualizarElementoDespesa(ElementoDespesaForm elementoDespesaForm, int id) {
        try {
            Optional<ElementoDespesaModel> elementoDespesaExistente = elementoDespesaRepository.findById(id);

            if (elementoDespesaExistente.isPresent()) {
                ElementoDespesaModel elementoDespesaAtualizado = elementoDespesaExistente.get();
                elementoDespesaAtualizado.setNome(elementoDespesaForm.getNome());
                elementoDespesaAtualizado.setCodigo(elementoDespesaForm.getCodigo()); // Adicione esta linha para atualizar o código
                elementoDespesaAtualizado.setDataAlteracao(LocalDateTime.now());
                elementoDespesaAtualizado = elementoDespesaRepository.save(elementoDespesaAtualizado);

                return modelMapper.map(elementoDespesaAtualizado, ElementoDespesaDto.class);
            } else {
                throw new DataIntegrityException("O Código do Elemento Despesa não existe na base de dados!");
            }
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException("Campo(s) obrigatório(s) do Elemento Despesa não foi(foram) preenchido(s).");
        }
    }


    public void RemoverElementoDespesa(int id) {
        try {
            if (elementoDespesaRepository.existsById(id)) {
                elementoDespesaRepository.deleteById(id);

            }else {
                throw new DataIntegrityException("O código do Elemento Despesa não existe na base de dados!");
            }
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException("Não é possível excluir um Elemento Despesa!");
        }
    }
}

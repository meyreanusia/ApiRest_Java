package br.com.ibm.orcamento.service;

import br.com.ibm.orcamento.model.GrupoDespesaModel;
import br.com.ibm.orcamento.repository.GrupoDespesaRepository;
import br.com.ibm.orcamento.rest.dto.GrupoDespesaDto;
import br.com.ibm.orcamento.rest.form.GrupoDespesaForm;
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
public class GrupoDespesaService {
    @Autowired
    private GrupoDespesaRepository grupoDespesaRepository;

    @Autowired
    private ModelMapper modelMapper;

    public GrupoDespesaDto ObterPorId(int id) {
        try {
            GrupoDespesaModel grupoDespesaModel = grupoDespesaRepository.findById(id).get();

            return modelMapper.map(grupoDespesaModel, GrupoDespesaDto.class);

        } catch (NoSuchElementException e) {
            throw new ObjectNotFoundException("Grupo Despesa não encontrado! Código : " + id + ", Tipo: " + GrupoDespesaModel.class.getName());
        }
    }

    public List<GrupoDespesaDto> ObterTodos(){
        try {
            List<GrupoDespesaModel> grupoDespesaList = grupoDespesaRepository.findAll();

            return grupoDespesaList.stream()
                    .map(grupoDespesa -> modelMapper.map(grupoDespesa, GrupoDespesaDto.class))
                    .collect(Collectors.toList());
        } catch (BusinessRuleException e) {
            throw new BusinessRuleException("Não é possível consultar os Grupos Despesa!");
        }
    }

    public GrupoDespesaDto SalvarGrupoDespesa(GrupoDespesaForm grupoDespesaForm) {
        try {
            GrupoDespesaModel grupoDespesaNovo = modelMapper.map(grupoDespesaForm, GrupoDespesaModel.class);

            grupoDespesaNovo = grupoDespesaRepository.save(grupoDespesaNovo);

            return modelMapper.map(grupoDespesaNovo, GrupoDespesaDto.class);

        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException("Campo(s) obrigatório(s) do Grupo Despesa não foi(foram) preenchido(s).");
        }
    }

    public GrupoDespesaDto AtualizarGrupoDespesa(GrupoDespesaForm grupoDespesaForm, int id) {
        try {
            Optional<GrupoDespesaModel> grupoDespesaExistente = grupoDespesaRepository.findById(id);

            if (grupoDespesaExistente.isPresent()) {
                GrupoDespesaModel grupoDespesaAtualizado = grupoDespesaExistente.get();
                grupoDespesaAtualizado.setNome(grupoDespesaForm.getNome());
                grupoDespesaAtualizado.setCodigo(grupoDespesaForm.getCodigo()); // Adicione esta linha para atualizar o código
                grupoDespesaAtualizado = grupoDespesaRepository.save(grupoDespesaAtualizado);

                return modelMapper.map(grupoDespesaAtualizado, GrupoDespesaDto.class);
            } else {
                throw new DataIntegrityException("O Código do Grupo Despesa não existe na base de dados!");
            }
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException("Campo(s) obrigatório(s) do Grupo Despesa não foi(foram) preenchido(s).");
        }
    }


    public void RemoverGrupoDespesa(int id) {
        try {
            if (grupoDespesaRepository.existsById(id)) {
                grupoDespesaRepository.deleteById(id);

            }else {
                throw new DataIntegrityException("O código do Grupo Despesa não existe na base de dados!");
            }
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException("Não é possível excluir um Grupo Despesa!");
        }
    }
}
package br.com.ibm.orcamento.service;

import br.com.ibm.orcamento.model.ElementoDespesaModel;
import br.com.ibm.orcamento.model.ProgramaModel;
import br.com.ibm.orcamento.repository.ElementoDespesaRepository;
import br.com.ibm.orcamento.repository.ProgramaRepository;
import br.com.ibm.orcamento.rest.dto.ElementoDespesaDto;
import br.com.ibm.orcamento.rest.dto.ProgramaDto;
import br.com.ibm.orcamento.rest.form.ElementoDespesaForm;
import br.com.ibm.orcamento.rest.form.ProgramaForm;
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
public class ProgramaService {
    @Autowired
    private ProgramaRepository programaRepository;

    @Autowired
    private ModelMapper modelMapper;

    public ProgramaDto ObterPorId(int id) {
        try {
            ProgramaModel programaModel = programaRepository.findById(id).get();

            return modelMapper.map(programaModel, ProgramaDto.class);

        } catch (NoSuchElementException e) {
            throw new ObjectNotFoundException("Programa não encontrado! Código : " + id + ", Tipo: " + ProgramaModel.class.getName());
        }
    }

    public List<ProgramaDto> ObterTodos(){
        try {
            List<ProgramaModel> programaList = programaRepository.findAll();

            return programaList.stream()
                    .map(programa -> modelMapper.map(programa, ProgramaDto.class))
                    .collect(Collectors.toList());
        } catch (BusinessRuleException e) {
            throw new BusinessRuleException("Não é possível consultar os Programas!");
        }
    }

    public ProgramaDto SalvarPrograma(ProgramaForm programaForm) {
        try {
            ProgramaModel programaNovo = modelMapper.map(programaForm, ProgramaModel.class);
            programaNovo.setDataCadastro(LocalDateTime.now());

            programaNovo = programaRepository.save(programaNovo);

            return modelMapper.map(programaNovo, ProgramaDto.class);

        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException("Campo(s) obrigatório(s) do Programa não foi(foram) preenchido(s).");
        }
    }

    public ProgramaDto AtualizarPrograma(ProgramaForm programaForm, int id) {
        try {
            Optional<ProgramaModel> programaExistente = programaRepository.findById(id);

            if (programaExistente.isPresent()) {
                ProgramaModel programaAtualizado = programaExistente.get();
                programaAtualizado.setNome(programaForm.getNome());
                programaAtualizado.setCodigo(programaForm.getCodigo()); // Adicione esta linha para atualizar o código
                programaAtualizado.setDataAlteracao(LocalDateTime.now());
                programaAtualizado = programaRepository.save(programaAtualizado);

                return modelMapper.map(programaAtualizado, ProgramaDto.class);
            } else {
                throw new DataIntegrityException("O Código do Programa não existe na base de dados!");
            }
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException("Campo(s) obrigatório(s) do Programa não foi(foram) preenchido(s).");
        }
    }


    public void RemoverPrograma(int id) {
        try {
            if (programaRepository.existsById(id)) {
                programaRepository.deleteById(id);

            }else {
                throw new DataIntegrityException("O código do Programa não existe na base de dados!");
            }
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException("Não é possível excluir um Programa!");
        }
    }
}

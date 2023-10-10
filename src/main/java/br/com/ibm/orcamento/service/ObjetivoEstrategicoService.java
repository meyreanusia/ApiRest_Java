package br.com.ibm.orcamento.service;

import br.com.ibm.orcamento.model.ObjetivoEstrategicoModel;
import br.com.ibm.orcamento.repository.ObjetivoEstrategicoRepository;
import br.com.ibm.orcamento.rest.dto.ObjetivoEstrategicoDto;
import br.com.ibm.orcamento.rest.form.ObjetivoEstrategicoForm;
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
public class ObjetivoEstrategicoService {

    @Autowired
    private ObjetivoEstrategicoRepository objetivoEstrategicoRepository;

    @Autowired
    private ModelMapper modelMapper;

    public ObjetivoEstrategicoDto ObterPorId(int id) {
        try {
            ObjetivoEstrategicoModel objetivoEstrategicoModel = objetivoEstrategicoRepository.findById(id).get();

            return modelMapper.map(objetivoEstrategicoModel, ObjetivoEstrategicoDto.class);

        } catch (NoSuchElementException e) {
            throw new ObjectNotFoundException("Objetivo Estrategico não encontrado! Código : " + id + ", Tipo: " + ObjetivoEstrategicoModel.class.getName());
        }
    }

    public List<ObjetivoEstrategicoDto> ObterTodos(){
        try {
            List<ObjetivoEstrategicoModel> objetivoEstrategicoList = objetivoEstrategicoRepository.findAll();

            return objetivoEstrategicoList.stream()
                    .map(objetivoEstrategico -> modelMapper.map(objetivoEstrategico, ObjetivoEstrategicoDto.class))
                    .collect(Collectors.toList());
        } catch (BusinessRuleException e) {
            throw new BusinessRuleException("Não é possível consultar os Objetivo Estrategicos!");
        }
    }

    public ObjetivoEstrategicoDto SalvarObjetivoEstrategico(ObjetivoEstrategicoForm objetivoEstrategicoForm) {
        try {
            ObjetivoEstrategicoModel objetivoEstrategicoNovo = modelMapper.map(objetivoEstrategicoForm, ObjetivoEstrategicoModel.class);
            objetivoEstrategicoNovo.setDataCadastro(LocalDateTime.now());

            objetivoEstrategicoNovo = objetivoEstrategicoRepository.save(objetivoEstrategicoNovo);

            return modelMapper.map(objetivoEstrategicoNovo, ObjetivoEstrategicoDto.class);

        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException("Campo(s) obrigatório(s) do Objetivo Estrategico não foi(foram) preenchido(s).");
        }
    }

    public ObjetivoEstrategicoDto AtualizarObjetivoEstrategico(ObjetivoEstrategicoForm objetivoEstrategicoForm, int id) {
        try {
            Optional<ObjetivoEstrategicoModel> objetivoEstrategicoExistente = objetivoEstrategicoRepository.findById(id);

            if (objetivoEstrategicoExistente.isPresent()) {
                ObjetivoEstrategicoModel objetivoEstrategicoAtualizado = objetivoEstrategicoExistente.get();
                objetivoEstrategicoAtualizado.setNome(objetivoEstrategicoForm.getNome());
                objetivoEstrategicoAtualizado.setDataAlteracao(LocalDateTime.now());
                objetivoEstrategicoAtualizado = objetivoEstrategicoRepository.save(objetivoEstrategicoAtualizado);

                return modelMapper.map(objetivoEstrategicoAtualizado, ObjetivoEstrategicoDto.class);
            }else{
                throw new DataIntegrityException("O Código do Objetivo Estrategico não existe na base de dados!");
            }
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException("Campo(s) obrigatório(s) do Objetivo Estrategico não foi(foram) preenchido(s).");
        }
    }

    public void RemoverObjetivoEstrategico(int id) {
        try {
            if (objetivoEstrategicoRepository.existsById(id)) {
                objetivoEstrategicoRepository.deleteById(id);

            }else {
                throw new DataIntegrityException("O código do Objetivo Estrategico não existe na base de dados!");
            }
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException("Não é possível excluir um Objetivo Estrategico!");
        }
    }
}

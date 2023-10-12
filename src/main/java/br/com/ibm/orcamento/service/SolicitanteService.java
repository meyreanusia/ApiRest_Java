package br.com.ibm.orcamento.service;

import br.com.ibm.orcamento.model.SolicitanteModel;
import br.com.ibm.orcamento.model.UnidadeModel;
import br.com.ibm.orcamento.repository.SolicitanteRepository;
import br.com.ibm.orcamento.repository.UnidadeRepository;
import br.com.ibm.orcamento.rest.dto.SolicitanteDto;
import br.com.ibm.orcamento.rest.dto.UnidadeDto;
import br.com.ibm.orcamento.rest.form.SolicitanteForm;
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
public class SolicitanteService {

    @Autowired
    private SolicitanteRepository solicitanteRepository;

    @Autowired
    private ModelMapper modelMapper;

    public SolicitanteDto ObterPorId(int id) {
        try {
            SolicitanteModel solicitanteModel = solicitanteRepository.findById(id).get();

            return modelMapper.map(solicitanteModel, SolicitanteDto.class);

        } catch (NoSuchElementException e) {
            throw new ObjectNotFoundException("Solicitante não encontrado! Código : " + id + ", Tipo: " + SolicitanteModel.class.getName());
        }
    }

    public List<SolicitanteDto> ObterTodos(){
        try {
            List<SolicitanteModel> solicitanteList = solicitanteRepository.findAll();

            return solicitanteList.stream()
                    .map(solicitante -> modelMapper.map(solicitante, SolicitanteDto.class))
                    .collect(Collectors.toList());
        } catch (BusinessRuleException e) {
            throw new BusinessRuleException("Não é possível consultar os Solicitantes!");
        }
    }

    public SolicitanteDto SalvarSolicitante(SolicitanteForm solicitanteForm) {
        try {
            SolicitanteModel solicitanteNovo = modelMapper.map(solicitanteForm, SolicitanteModel.class);
            solicitanteNovo.setDataCadastro(LocalDateTime.now());

            solicitanteNovo = solicitanteRepository.save(solicitanteNovo);

            return modelMapper.map(solicitanteNovo, SolicitanteDto.class);

        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException("Campo(s) obrigatório(s) do Solicitante não foi(foram) preenchido(s).");
        }
    }

    public SolicitanteDto AtualizarSolicitante(SolicitanteForm solicitanteForm, int id) {
        try {
            Optional<SolicitanteModel> solicitanteExistente = solicitanteRepository.findById(id);

            if (solicitanteExistente.isPresent()) {
                SolicitanteModel solicitanteAtualizado = solicitanteExistente.get();
                solicitanteAtualizado.setNome(solicitanteForm.getNome());
                solicitanteAtualizado.setDataAlteracao(LocalDateTime.now());
                solicitanteAtualizado = solicitanteRepository.save(solicitanteAtualizado);

                return modelMapper.map(solicitanteAtualizado, SolicitanteDto.class);
            }else{
                throw new DataIntegrityException("O Código do Solicitante não existe na base de dados!");
            }
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException("Campo(s) obrigatório(s) do Solicitante não foi(foram) preenchido(s).");
        }
    }

    public void RemoverSolicitante(int id) {
        try {
            if (solicitanteRepository.existsById(id)) {
                solicitanteRepository.deleteById(id);

            }else {
                throw new DataIntegrityException("O código do Solicitante não existe na base de dados!");
            }
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException("Não é possível excluir um Solicitante!");
        }
    }
}

package br.com.ibm.orcamento.service;

import br.com.ibm.orcamento.model.FonteRecursoModel;
import br.com.ibm.orcamento.repository.FonteRecursoRepository;
import br.com.ibm.orcamento.rest.dto.FonteRecursoDto;
import br.com.ibm.orcamento.rest.form.FonteRecursoForm;
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
public class FonteRecursoSercice {
    @Autowired
    private FonteRecursoRepository fonteRecursoRepository;

    @Autowired
    private ModelMapper modelMapper;

    public FonteRecursoDto ObterPorId(int id) {
        try {
            FonteRecursoModel fonteRecursoModel = fonteRecursoRepository.findById(id).get();
            return modelMapper.map(fonteRecursoModel, FonteRecursoDto.class);

        } catch (NoSuchElementException e) {
            throw new ObjectNotFoundException("Fonte Recurso não encontrado! Código : " + id + ", Tipo: " + FonteRecursoModel.class.getName());
        }
    }

    public List<FonteRecursoDto> ObterTodos(){
        try {
            List<FonteRecursoModel> fonteRecursoModelList = fonteRecursoRepository.findAll();

            return fonteRecursoModelList.stream()
                    .map(fonteRecurso -> modelMapper.map(fonteRecurso, FonteRecursoDto.class))
                    .collect(Collectors.toList());
        } catch (BusinessRuleException e) {
            throw new BusinessRuleException("Não é possível consultar as Fontes de Recursos!");
        }
    }

    public FonteRecursoDto salvarFonteRecurso(FonteRecursoForm fonteRecursoForm) {
        try {
            FonteRecursoModel fonteRecursoNovo = modelMapper.map(fonteRecursoForm, FonteRecursoModel.class);

            fonteRecursoNovo = fonteRecursoRepository.save(fonteRecursoNovo);

            return modelMapper.map(fonteRecursoNovo, FonteRecursoDto.class);

        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException("Campo(s) obrigatório(s) do Tipo Lançamento não foi(foram) preenchido(s).");
        }
    }

    public FonteRecursoDto AtualizarFonteRecurso(FonteRecursoForm fonteRecursoForm, int id) {
        try {
            Optional<FonteRecursoModel> fonteRecursoExistente = fonteRecursoRepository.findById(id);

            if (fonteRecursoExistente.isPresent()) {
                FonteRecursoModel fonteRecursoAtualizado = fonteRecursoExistente.get();
                fonteRecursoAtualizado.setNome(fonteRecursoForm.getNome());
                fonteRecursoAtualizado.setCodigo(fonteRecursoForm.getCodigo());
                fonteRecursoAtualizado = fonteRecursoRepository.save(fonteRecursoAtualizado);

                return modelMapper.map(fonteRecursoAtualizado, FonteRecursoDto.class);
            }else{
                throw new DataIntegrityException("O Código da Fonte de Recurso não existe na base de dados!");
            }
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException("Campo(s) obrigatório(s) da Fonte de Recurso  não foi(foram) preenchido(s).");
        }
    }

    public void RemoverFonteRecurso(int id) {
        try {
            if (fonteRecursoRepository.existsById(id)) {
                fonteRecursoRepository.deleteById(id);

            }else {
                throw new DataIntegrityException("O código da Fonte de Recurso não existe na base de dados!");
            }
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException("Não é possível excluir uma Fonte de Recurso!");
        }
    }
}

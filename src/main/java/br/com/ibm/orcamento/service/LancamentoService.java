package br.com.ibm.orcamento.service;

import br.com.ibm.orcamento.model.ConsultaLancamento;
import br.com.ibm.orcamento.model.GrupoDespesaModel;
import br.com.ibm.orcamento.model.LancamentoModel;
import br.com.ibm.orcamento.repository.LancamentoRepository;
import br.com.ibm.orcamento.rest.dto.GrupoDespesaDto;
import br.com.ibm.orcamento.rest.dto.LancamentoDto;
import br.com.ibm.orcamento.rest.form.LancamentoForm;
import br.com.ibm.orcamento.rest.form.LancamentoUpdateForm;
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
public class LancamentoService {

    @Autowired
    LancamentoRepository lancamentoRepository;

    @Autowired
    ModelMapper modelMapper;

    public List<LancamentoDto> ObterTodos(){
        try {
            List<ConsultaLancamento> consultaLancamentoList = lancamentoRepository.findLancamentos();

            return consultaLancamentoList.stream()
                    .map(lancamento -> modelMapper.map(lancamento, LancamentoDto.class))
                    .collect(Collectors.toList());
        } catch (BusinessRuleException e) {
            throw new BusinessRuleException("Não é possível consultar os Lançamentos!");
        }
    }

    public LancamentoDto ObterPorId(int id) {
        try {
            ConsultaLancamento consultaLancamento = lancamentoRepository.findLancamentosPorId(id);
            if (consultaLancamento == null)
                throw new ObjectNotFoundException("Lançamento não encontrado! Código : " + id + ", Tipo: " + LancamentoModel.class.getName());
            return modelMapper.map(consultaLancamento, LancamentoDto.class);

        } catch (NoSuchElementException e) {
            throw new ObjectNotFoundException("Lançamento não encontrado! Código : " + id + ", Tipo: " + LancamentoModel.class.getName());
        }
    }

    public LancamentoDto SalvarLancamento(LancamentoForm lancamentoForm) {
        try {
            LancamentoModel lancamentoNovo = modelMapper.map(lancamentoForm, LancamentoModel.class);
            lancamentoNovo.setDataCadastro(LocalDateTime.now());

            lancamentoNovo = lancamentoRepository.save(lancamentoNovo);
            ConsultaLancamento lancamento = lancamentoRepository.findLancamentosPorId(lancamentoNovo.getId());

            return modelMapper.map(lancamento, LancamentoDto.class);

        } catch (DataIntegrityViolationException e) {
            if (e.getMessage().contains("FK")) {
                String mensagemDeErro = e.getMessage();
                // Divide a mensagem pelo ponto e vírgula
                String[] partes = mensagemDeErro.split(";\\s");
                String terceiraParte = partes[2];
                throw new DataIntegrityException(terceiraParte);
            }
            throw new DataIntegrityException("Campo(s) obrigatório(s) do Lançamento não foi(foram) preenchido(s).");
        }
    }

    public LancamentoDto AtualizarLancamento(LancamentoUpdateForm lancamentoUpdateForm, int id) {
        try {
            Optional<LancamentoModel> lancamentoExistente = lancamentoRepository.findById(id);

            if (lancamentoExistente.isPresent()) {
                LancamentoModel lancamentoAtualizado = lancamentoExistente.get();
                lancamentoAtualizado = modelMapper.map(lancamentoUpdateForm, LancamentoModel.class);
                lancamentoAtualizado.setDataAlteracao(LocalDateTime.now());
                lancamentoAtualizado = lancamentoRepository.save(lancamentoAtualizado);
                ConsultaLancamento lancamento = lancamentoRepository.findLancamentosPorId(lancamentoAtualizado.getId());

                return modelMapper.map(lancamento, LancamentoDto.class);
            } else {
                throw new DataIntegrityException("O Código do Lançamento não existe na base de dados!");
            }
        } catch (DataIntegrityViolationException e) {
            if (e.getMessage().contains("FK")) {
                String mensagemDeErro = e.getMessage();
                // Divide a mensagem pelo ponto e vírgula
                String[] partes = mensagemDeErro.split(";\\s");
                String terceiraParte = partes[2];
                throw new DataIntegrityException(terceiraParte);
            }
            throw new DataIntegrityException("Campo(s) obrigatório(s) do Lançamento não foi(foram) preenchido(s).");
        }
    }

    public void RemoverLancamento(int id) {
        try {
            if (lancamentoRepository.existsById(id)) {
                lancamentoRepository.deleteById(id);

            }else {
                throw new DataIntegrityException("O código do Lançamento não existe na base de dados!");
            }
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException("Não é possível excluir um Lançamento!");
        }
    }
}
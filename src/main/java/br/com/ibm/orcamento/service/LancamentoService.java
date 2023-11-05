package br.com.ibm.orcamento.service;

import br.com.ibm.orcamento.model.ConsultaLancamento;
import br.com.ibm.orcamento.model.LancamentoModel;
import br.com.ibm.orcamento.repository.LancamentoRepository;
import br.com.ibm.orcamento.rest.dto.LancamentoDto;
import br.com.ibm.orcamento.rest.form.LancamentoForm;
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

    public LancamentoDto AtualizarLancamento(LancamentoForm lancamentoUpdateForm, int id) {
        try {
            Optional<LancamentoModel> lancamentoExistente = lancamentoRepository.findById(id);

            if (lancamentoExistente.isPresent()) {
                LancamentoModel lancamentoAtualizado = lancamentoExistente.get();
                lancamentoAtualizado = CamposAtualizados(lancamentoUpdateForm, lancamentoAtualizado);

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

    public LancamentoModel CamposAtualizados(LancamentoForm lancamentoUpdateForm, LancamentoModel lancamentoAtualizado)
    {
        if (lancamentoUpdateForm.getNumeroLancamento() > 0) lancamentoAtualizado.setNumeroLancamento(lancamentoUpdateForm.getNumeroLancamento());
        if (lancamentoUpdateForm.getDescricao() != null) lancamentoAtualizado.setDescricao(lancamentoUpdateForm.getDescricao());
        if (lancamentoUpdateForm.getIdLancamentoPai() != null) lancamentoAtualizado.setIdLancamentoPai(lancamentoUpdateForm.getIdLancamentoPai());
        if (lancamentoUpdateForm.getValor() > 0) lancamentoAtualizado.setValor(lancamentoUpdateForm.getValor());
        if (lancamentoUpdateForm.getIdTipoLancamento() > 0) lancamentoAtualizado.setIdTipoLancamento(lancamentoUpdateForm.getIdTipoLancamento());
        if (lancamentoUpdateForm.getIdUnidade() > 0) lancamentoAtualizado.setIdUnidade(lancamentoUpdateForm.getIdUnidade());
        if (lancamentoUpdateForm.getIdUnidadeOrcamentaria() > 0) lancamentoAtualizado.setIdUnidadeOrcamentaria(lancamentoUpdateForm.getIdUnidadeOrcamentaria());
        if (lancamentoUpdateForm.getIdPrograma() > 0) lancamentoAtualizado.setIdPrograma(lancamentoUpdateForm.getIdPrograma());
        if (lancamentoUpdateForm.getIdAcao() > 0) lancamentoAtualizado.setIdAcao(lancamentoUpdateForm.getIdAcao());
        if (lancamentoUpdateForm.getIdFonteRecurso() > 0) lancamentoAtualizado.setIdFonteRecurso(lancamentoUpdateForm.getIdFonteRecurso());
        if (lancamentoUpdateForm.getIdGrupoDespesa() > 0) lancamentoAtualizado.setIdGrupoDespesa(lancamentoUpdateForm.getIdGrupoDespesa());
        if (lancamentoUpdateForm.getIdModalidadeAplicacao() > 0) lancamentoAtualizado.setIdModalidadeAplicacao(lancamentoUpdateForm.getIdModalidadeAplicacao());
        if (lancamentoUpdateForm.getIdElementoDespesa() > 0) lancamentoAtualizado.setIdElementoDespesa(lancamentoUpdateForm.getIdElementoDespesa());
        if (lancamentoUpdateForm.getIdSolicitante() != null) lancamentoAtualizado.setIdSolicitante(lancamentoUpdateForm.getIdSolicitante());
        if (lancamentoUpdateForm.getIdObjetivoEstrategico() != null) lancamentoAtualizado.setIdObjetivoEstrategico(lancamentoUpdateForm.getIdObjetivoEstrategico());
        if (lancamentoUpdateForm.getIdTipoTransacao() > 0) lancamentoAtualizado.setIdTipoTransacao(lancamentoUpdateForm.getIdTipoTransacao());
        if (lancamentoUpdateForm.getGed() != null) lancamentoAtualizado.setGed(lancamentoUpdateForm.getGed());
        if (lancamentoUpdateForm.getContratado() != null) lancamentoAtualizado.setContratado(lancamentoUpdateForm.getContratado());
        if (lancamentoUpdateForm.getAnoOrcamento() > 0) lancamentoAtualizado.setAnoOrcamento(lancamentoUpdateForm.getAnoOrcamento());

        return lancamentoAtualizado;
    }
}
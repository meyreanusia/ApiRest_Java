package br.com.ibm.orcamento.rest.form;

import lombok.Data;

import javax.validation.constraints.*;
import java.time.LocalDate;

@Data
public class LancamentoUpdateForm {
    private boolean lancamentoInvalido;
    private int numeroLancamento;
    private String descricao;
    private LocalDate dataLancamento;
    private Integer idLancamentoPai;
    private float valor;
    private int idTipoLancamento;
    private int idUnidade;
    private int idUnidadeOrcamentaria;
    private int idPrograma;
    private int idAcao;
    private int idFonteRecurso;
    private int idGrupoDespesa;
    private int idModalidadeAplicacao;
    private int idElementoDespesa;
    private Integer idSolicitante;
    private Integer idObjetivoEstrategico;
    private int idTipoTransacao;
    private String ged;
    private String contratado;
    private short anoOrcamento;
}

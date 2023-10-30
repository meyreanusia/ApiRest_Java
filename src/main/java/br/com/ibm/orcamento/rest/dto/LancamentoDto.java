package br.com.ibm.orcamento.rest.dto;

import lombok.Data;

import java.util.Date;

@Data
public class LancamentoDto {
    private int id;
    private Boolean lancamentoInvalido;
    private int numeroLancamento;
    private String descricao;
    private Date dataLancamento;
    private int idLancamentoPai;
    private float valor;
    private String dsTipoLancamento;
    private String dsUnidade;
    private String dsUnidadeOrcamentaria;
    private String dsPrograma;
    private String dsAcao;
    private String dsFonteRecurso;
    private String dsGrupoDespesa;
    private String dsModalidadeAplicacao;
    private String dsElementoDespesa;
    private String dsSolicitante;
    private String dsObjetivoEstrategico;
    private String dsTipoTransacao;
    private String GED;
    private String contratado;
    private short anoOrcamento;
}

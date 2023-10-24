package br.com.ibm.orcamento.model;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Data
@Table(name="tbLancamentos")
public class LancamentoModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "lancamentoInvalido", nullable = false)
    private int lancamentoInvalido;

    @Column(name = "numeroLancamento")
    private int numeroLancamento;

    @Column(name = "idTipoLancamento", nullable = false)
    private int idTipoLancamento;

    @Column(name = "dataLancamento", nullable = false)
    private Date dataLancamento;

    @Column(name = "idLancamentoPai")
    private Integer idLancamentoPai;

    @Column(name = "idUnidade", nullable = false)
    private int idUnidade;

    @Column(name = "descricao", length = 255, nullable = false)
    private String descricao;

    @Column(name = "idUnidadeOrcamentaria", nullable = false)
    private int idUnidadeOrcamentaria;

    @Column(name = "idPrograma", nullable = false)
    private int idPrograma;

    @Column(name = "idAcao", nullable = false)
    private int idAcao;

    @Column(name = "idFonteRecurso", nullable = false)
    private int idFonteRecurso;

    @Column(name = "idGrupoDespesa", nullable = false)
    private int idGrupoDespesa;

    @Column(name = "idModalidadeAplicacao", nullable = false)
    private int idModalidadeAplicacao;

    @Column(name = "idElementoDespesa", nullable = false)
    private int idElementoDespesa;

    @Column(name = "idSolicitante")
    private Integer idSolicitante;

    @Column(name = "ged", length = 27)
    private String ged;

    @Column(name = "contratado", length = 255)
    private String contratado;

    @Column(name = "idObjetivoEstrategico")
    private Integer idObjetivoEstrategico;

    @Column(name = "valor", nullable = false)
    private float valor;

    @Column(name = "idTipoTransacao", nullable = false)
    private int idTipoTransacao;

    @Column(name = "dataCadastro", nullable = false)
    private LocalDateTime dataCadastro;

    @Column(name = "dataAlteracao")
    private LocalDateTime dataAlteracao;

    @Column(name = "anoOrcamento", nullable = false)
    private short anoOrcamento;
}

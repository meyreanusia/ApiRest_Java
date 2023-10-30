package br.com.ibm.orcamento.rest.form;

import lombok.Data;

import javax.validation.constraints.*;
import java.time.LocalDate;

@Data
public class LancamentoForm {
    @NotNull(message = "O campo 'lancamentoInvalido' não pode ser nulo.")
    private boolean lancamentoInvalido;

    private int numeroLancamento;

    @NotEmpty
    @NotBlank(message = "A descrição não pode estar em branco.")
    @Size(max = 255)
    private String descricao;

    @NotNull(message = "Data de Lançamento não pode ser nulo.")
    @FutureOrPresent(message = "Data de Lançamento deve ser data atual ou futura.")
    private LocalDate dataLancamento;

    private Integer idLancamentoPai;

    @NotNull(message = "Valor não pode ser nulo.")
    @Positive(message = "Valor do Lançamento não pode ser menor ou igual a zero")
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

    @Size(max = 255)
    private String contratado;

    private short anoOrcamento;
}
package br.com.ibm.orcamento.rest.form;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.validation.constraints.*;
import java.time.LocalDate;

@Data
public class LancamentoForm {
    @NotNull(message = "O campo 'lancamentoInvalido' não pode ser nulo.")
    private boolean lancamentoInvalido;

    @NotNull(message = "O campo 'numeroLancamento' não pode ser nulo.")
    private int numeroLancamento;

    @NotEmpty
    @NotBlank(message = "A descrição não pode estar em branco.")
    @Size(max = 255)
    private String descricao;

    @NotNull(message = "Data de Lançamento não pode ser nulo.")
    @FutureOrPresent(message = "Data de Lançamento deve ser data atual ou futura.")
    @JsonFormat(pattern = "yyyy/MM/dd")
    private LocalDate dataLancamento;

    private Integer idLancamentoPai;

    @NotNull(message = "Valor não pode ser nulo.")
    @Positive(message = "Valor do Lançamento não pode ser menor ou igual a zero")
    private float valor;

    @NotNull(message = "O campo 'idTipoLancamento' não pode ser nulo.")
    private int idTipoLancamento;

    @NotNull(message = "O campo 'idUnidade' não pode ser nulo.")
    private int idUnidade;

    @NotNull(message = "O campo 'idUnidadeOrcamentaria' não pode ser nulo.")
    private int idUnidadeOrcamentaria;

    @NotNull(message = "O campo 'idPrograma' não pode ser nulo.")
    private int idPrograma;

    @NotNull(message = "O campo 'idAcao' não pode ser nulo.")
    private int idAcao;

    @NotNull(message = "O campo 'idFonteRecurso' não pode ser nulo.")
    private int idFonteRecurso;

    @NotNull(message = "O campo 'idTipoLancamento' não pode ser nulo.")
    private int idGrupoDespesa;

    @NotNull(message = "O campo 'idModalidadeAplicacao' não pode ser nulo.")
    private int idModalidadeAplicacao;

    @NotNull(message = "O campo 'idElementoDespesa' não pode ser nulo.")
    private int idElementoDespesa;
    private Integer idSolicitante;
    private Integer idObjetivoEstrategico;

    @NotNull(message = "O campo 'idTipoTransacao' não pode ser nulo.")
    private int idTipoTransacao;

    @NotEmpty
    @NotBlank(message = "A 'GED' não pode estar em branco.")
    @Size(max = 27)
    private String ged;

    @NotEmpty
    @NotBlank(message = "O 'contratado' não pode estar em branco.")
    @Size(max = 255)
    private String contratado;

    @NotNull(message = "O campo 'anoOrcamento' não pode ser nulo.")
    @JsonFormat(pattern = "yyyy")
    private short anoOrcamento;
}
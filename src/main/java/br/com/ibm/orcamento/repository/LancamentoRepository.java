package br.com.ibm.orcamento.repository;

import br.com.ibm.orcamento.model.ConsultaLancamento;
import br.com.ibm.orcamento.model.LancamentoModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface LancamentoRepository extends JpaRepository<LancamentoModel, Integer> {

    //Consulta criada para consultar os lançamentos e trazer os nomes das tabelas de Apoio
    @Query(value = "SELECT \n" +
            "L.id,\n" +
            "L.lancamentoInvalido,\n" +
            "L.numeroLancamento,\n" +
            "L.descricao,\n" +
            "L.dataLancamento,\n" +
            "L.idLancamentoPai,\n" +
            "L.valor,\n" +
            "TL.nome as dsTipoLancamento,\n" +
            "U.nome AS dsUnidade,\n" +
            "UO.nome AS dsUnidadeOrcamentaria,\n" +
            "P.nome AS dsPrograma,\n" +
            "A.nome AS dsAcao,\n" +
            "FR.nome AS dsFonteRecurso,\n" +
            "GD.nome AS dsGrupoDespesa,\n" +
            "MA.nome AS dsModalidadeAplicacao,\n" +
            "ED.nome AS dsElementoDespesa,\n" +
            "CASE WHEN S.nome IS NULL THEN 'Não Informado' ELSE S.nome END AS dsSolicitante,\n" +
            "CASE WHEN OE.nome IS NULL THEN 'Não Informado' ELSE OE.nome END AS dsObjetivoEstrategico,\n" +
            "TT.nome AS dsTipoTransacao,\n" +
            "L.GED,\n" +
            "L.contratado,\n" +
            "L.anoOrcamento\n" +
            "FROM TBLANCAMENTOS L\n" +
            "JOIN TBTIPOLANCAMENTO TL ON L.idTipoLancamento = TL.id\n" +
            "JOIN TBUNIDADE U ON L.idUnidade = U.id\n" +
            "JOIN TBUNIDADEORCAMENTARIA UO ON L.idUnidadeOrcamentaria = UO.id\n" +
            "JOIN TBELEMENTODESPESA ED ON L.idElementoDespesa =ED.id\n" +
            "JOIN TBACAO A ON L.idAcao = A.id\n" +
            "JOIN TBPROGRAMA P ON L.idPrograma = P.id\n" +
            "LEFT JOIN TBSOLICITANTE S ON L.idSolicitante = S.id\n" +
            "LEFT JOIN TBOBJETIVOESTRATEGICO OE ON L.idObjetivoEstrategico = OE.id\n" +
            "JOIN TBGRUPODESPESA GD ON L.idGrupoDespesa = GD.id\n" +
            "JOIN TBMODALIDADEAPLICACAO MA ON L.idModalidadeAplicacao = MA.id\n" +
            "JOIN TBTIPOTRANSACAO TT ON L.idTipoTransacao = TT.id\n" +
            "JOIN TBFONTERECURSO FR ON L.idFonteRecurso = FR.id", nativeQuery = true)
    List<ConsultaLancamento> findLancamentos();

    @Query(value = "SELECT \n" +
            "L.id,\n" +
            "L.lancamentoInvalido,\n" +
            "L.numeroLancamento,\n" +
            "L.descricao,\n" +
            "L.dataLancamento,\n" +
            "L.idLancamentoPai,\n" +
            "L.valor,\n" +
            "TL.nome as dsTipoLancamento,\n" +
            "U.nome AS dsUnidade,\n" +
            "UO.nome AS dsUnidadeOrcamentaria,\n" +
            "P.nome AS dsPrograma,\n" +
            "A.nome AS dsAcao,\n" +
            "FR.nome AS dsFonteRecurso,\n" +
            "GD.nome AS dsGrupoDespesa,\n" +
            "MA.nome AS dsModalidadeAplicacao,\n" +
            "ED.nome AS dsElementoDespesa,\n" +
            "CASE WHEN S.nome IS NULL THEN 'Não Informado' ELSE S.nome END AS dsSolicitante,\n" +
            "CASE WHEN OE.nome IS NULL THEN 'Não Informado' ELSE OE.nome END AS dsObjetivoEstrategico,\n" +
            "TT.nome AS dsTipoTransacao,\n" +
            "L.GED,\n" +
            "L.contratado,\n" +
            "L.anoOrcamento\n" +
            "FROM TBLANCAMENTOS L\n" +
            "JOIN TBTIPOLANCAMENTO TL ON L.idTipoLancamento = TL.id\n" +
            "JOIN TBUNIDADE U ON L.idUnidade = U.id\n" +
            "JOIN TBUNIDADEORCAMENTARIA UO ON L.idUnidadeOrcamentaria = UO.id\n" +
            "JOIN TBELEMENTODESPESA ED ON L.idElementoDespesa =ED.id\n" +
            "JOIN TBACAO A ON L.idAcao = A.id\n" +
            "JOIN TBPROGRAMA P ON L.idPrograma = P.id\n" +
            "LEFT JOIN TBSOLICITANTE S ON L.idSolicitante = S.id\n" +
            "LEFT JOIN TBOBJETIVOESTRATEGICO OE ON L.idObjetivoEstrategico = OE.id\n" +
            "JOIN TBGRUPODESPESA GD ON L.idGrupoDespesa = GD.id\n" +
            "JOIN TBMODALIDADEAPLICACAO MA ON L.idModalidadeAplicacao = MA.id\n" +
            "JOIN TBTIPOTRANSACAO TT ON L.idTipoTransacao = TT.id\n" +
            "JOIN TBFONTERECURSO FR ON L.idFonteRecurso = FR.id WHERE L.id = :id", nativeQuery = true)
    Optional<ConsultaLancamento> findLancamentosPorId(@Param("id") int id);
}

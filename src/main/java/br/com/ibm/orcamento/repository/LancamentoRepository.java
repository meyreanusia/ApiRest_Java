package br.com.ibm.orcamento.repository;

import br.com.ibm.orcamento.model.ConsultaLancamento;
import br.com.ibm.orcamento.model.LancamentoModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface LancamentoRepository extends JpaRepository<LancamentoModel, Integer> {

    //VIEW VW_LANCAMENTOS criada para consultar os lançamentos e trazer os nomes das tabelas de Apoio
    //Para visualizar a VIEW, verificar o arquivo Data.sql no caminho src > main > resources
    @Query(value = "SELECT * FROM VW_LANCAMENTOS", nativeQuery = true)
    List<ConsultaLancamento> findLancamentos();

    @Query(value = "SELECT * FROM VW_LANCAMENTOS WHERE id = :id", nativeQuery = true)
    Optional<ConsultaLancamento> findLancamentosPorId(@Param("id") int id);
}

package br.com.ibm.orcamento.repository;

import br.com.ibm.orcamento.model.TipoLancamentoModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TipoLancamentoRepository extends JpaRepository<TipoLancamentoModel, Integer> {
}


package br.com.ibm.orcamento.repository;

import br.com.ibm.orcamento.model.TipoTransacaoModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface TipoTransacaoRepository extends JpaRepository<TipoTransacaoModel, Integer> {
}

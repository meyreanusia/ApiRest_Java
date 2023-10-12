package br.com.ibm.orcamento.repository;

import br.com.ibm.orcamento.model.GrupoDespesaModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GrupoDespesaRepository extends JpaRepository<GrupoDespesaModel, Integer> {
}

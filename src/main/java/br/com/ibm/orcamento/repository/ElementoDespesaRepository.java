package br.com.ibm.orcamento.repository;

import br.com.ibm.orcamento.model.ElementoDespesaModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ElementoDespesaRepository extends JpaRepository<ElementoDespesaModel, Integer> {
}

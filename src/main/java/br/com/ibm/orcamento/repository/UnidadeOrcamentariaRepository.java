package br.com.ibm.orcamento.repository;

import br.com.ibm.orcamento.model.UnidadeOrcamentariaModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UnidadeOrcamentariaRepository extends JpaRepository<UnidadeOrcamentariaModel, Integer> {
}

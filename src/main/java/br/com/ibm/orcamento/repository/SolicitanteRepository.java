package br.com.ibm.orcamento.repository;

import br.com.ibm.orcamento.model.SolicitanteModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SolicitanteRepository extends JpaRepository<SolicitanteModel, Integer> {
}

package br.com.ibm.orcamento.repository;

import br.com.ibm.orcamento.model.AcaoModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AcaoRepository extends JpaRepository<AcaoModel , Integer> {
}

package br.com.ibm.orcamento.repository;

import br.com.ibm.orcamento.model.ProgramaModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProgramaRepository extends JpaRepository<ProgramaModel, Integer> {
}

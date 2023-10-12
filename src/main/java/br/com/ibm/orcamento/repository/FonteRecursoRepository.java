package br.com.ibm.orcamento.repository;

import br.com.ibm.orcamento.model.FonteRecursoModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FonteRecursoRepository extends JpaRepository<FonteRecursoModel, Integer> {
}

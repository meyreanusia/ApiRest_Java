package br.com.ibm.orcamento.repository;

import br.com.ibm.orcamento.model.LancamentoModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LancamentoRepository extends JpaRepository<LancamentoModel, Integer> {
}

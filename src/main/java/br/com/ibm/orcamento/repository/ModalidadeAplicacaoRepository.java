package br.com.ibm.orcamento.repository;

import br.com.ibm.orcamento.model.ModalidadeAplicacaoModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ModalidadeAplicacaoRepository extends JpaRepository<ModalidadeAplicacaoModel, Integer> {
}

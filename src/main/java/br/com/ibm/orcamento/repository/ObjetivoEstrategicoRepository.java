package br.com.ibm.orcamento.repository;

import br.com.ibm.orcamento.model.ObjetivoEstrategicoModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ObjetivoEstrategicoRepository extends JpaRepository<ObjetivoEstrategicoModel, Integer> {
}

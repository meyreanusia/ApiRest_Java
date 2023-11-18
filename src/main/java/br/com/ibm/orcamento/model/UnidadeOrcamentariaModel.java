package br.com.ibm.orcamento.model;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
@Table(name="tbUnidadeOrcamentaria")
public class UnidadeOrcamentariaModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "codigo", nullable = false, unique = true)
    private int codigo;

    @Column(name = "nome", length = 255, nullable = false)
    private String nome;

    @Column(name = "dataCadastro", nullable = false)
    private LocalDateTime dataCadastro;

    @Column(name = "dataAlteracao")
    private LocalDateTime dataAlteracao;

    //Anotação para anter de persistir um novo registro no banco, preencher a data de cadastro.
    @PrePersist
    public void prePersist()
    {
        this.setDataCadastro(LocalDateTime.now());
    }

    //Anotação para anter de atualizar um registro no banco, preencher a data de atualização.
    @PreUpdate
    public void preUpdate()
    {
        this.setDataAlteracao(LocalDateTime.now());
    }
}

package br.com.ibm.orcamento.model;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Data
@Table(name="tbUnidade")
public class UnidadeModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "nome", length = 255, nullable = false)
    private String nome;

    @Column(name = "dataCadastro", nullable = false)
    private LocalDateTime dataCadastro;

    @Column(name = "dataAlteracao")
    private LocalDateTime dataAlteracao;

}

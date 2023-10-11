package br.com.ibm.orcamento.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Data
@Table(name="tbElementoDespesa")
public class ElementoDespesaModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "codigo", nullable = false, unique = true)
    //@NotNull(message = "O campo 'codigo' não pode ser nulo")
    private int codigo;

    @Column(name = "nome", length = 255, nullable = false)
    private String nome;

    @Column(name = "dataCadastro", nullable = false)
    private LocalDateTime dataCadastro;

    @Column(name = "dataAlteracao")
    private LocalDateTime dataAlteracao;
}

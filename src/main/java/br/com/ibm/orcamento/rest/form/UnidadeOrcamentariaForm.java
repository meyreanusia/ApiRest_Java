package br.com.ibm.orcamento.rest.form;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class UnidadeOrcamentariaForm {
    @NotEmpty
    @NotBlank(message = "O Nome não pode estar em branco.")
    @Size(max = 255)
    private String nome;

    @NotNull(message = "O campo 'codigo' não pode ser nulo")
    private int codigo;
}

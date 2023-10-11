package br.com.ibm.orcamento.rest.form;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
public class ElementoDespesaForm {
    @NotEmpty
    @NotBlank(message = "O Nome não pode estar em branco.")
    @Size(max = 255)
    private String nome;

    @NotEmpty
    @NotBlank(message = "O Código não pode estar em branco.")
    private int codigo;
}

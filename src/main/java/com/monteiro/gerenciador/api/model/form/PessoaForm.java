package com.monteiro.gerenciador.api.model.form;

import java.time.LocalDate;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PessoaForm {
	@NotBlank
	private String nome;
	@NotNull
	@JsonFormat(pattern="dd/MM/yyyy") 
    private LocalDate dataNascimento;


}

package com.monteiro.gerenciador.api.model.form;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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

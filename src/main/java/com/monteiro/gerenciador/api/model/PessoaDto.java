package com.monteiro.gerenciador.api.model;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PessoaDto {

	private Long id;
	private String nome;
	@JsonFormat(pattern="dd/MM/yyyy") 
	private LocalDate dataNascimento;
}

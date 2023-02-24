package com.monteiro.gerenciador.api.model;

import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PessoaDto {

	private Long id;
	private String nome;
	@JsonFormat(pattern="dd/MM/yyyy") 
	private LocalDate dataDeNascimento;
	private List<EnderecoDto> enderecos;
	private EnderecoDto enderecoPrincipal;
}

package com.monteiro.gerenciador.api.model.form;

import java.time.LocalDate;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PessoaForm {
	
	private String nome;
	private LocalDate dataDeNascimento;
	private EnderecoIdForm enderecos;
	private EnderecoIdForm enderecoPrincipal;

}

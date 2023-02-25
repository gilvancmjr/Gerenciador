package com.monteiro.gerenciador.api.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EnderecoDto {

	private Long id;
	private String logradouro;
	private String cep;
	private int numero;
	private String cidade;
	private boolean principal;
	

}

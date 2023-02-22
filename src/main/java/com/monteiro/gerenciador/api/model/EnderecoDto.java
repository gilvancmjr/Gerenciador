package com.monteiro.gerenciador.api.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EnderecoDto {

	private String logradouro;
	private int cep;
	private int numero;
	private String cidade;

}

package com.monteiro.gerenciador.api.model.form;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EnderecoForm {
	private Long id;
	private String logradouro;
	private String cep;
	private int numero;
	private String cidade;
	

}

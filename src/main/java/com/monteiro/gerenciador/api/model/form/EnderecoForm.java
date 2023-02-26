package com.monteiro.gerenciador.api.model.form;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EnderecoForm {
	@NotBlank
	private String logradouro;
	@NotBlank
	private String cep;
	@NotNull @Min(1)
	private int numero;
	@NotBlank
	private String cidade;
	@NotNull
	private boolean principal;
	

}

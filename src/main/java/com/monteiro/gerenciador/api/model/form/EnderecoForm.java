package com.monteiro.gerenciador.api.model.form;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModelProperty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EnderecoForm {
	@ApiModelProperty(example = "Rua E", dataType = "String")
	@NotBlank
	private String logradouro;
	@ApiModelProperty(example = "12345-678", dataType = "String")
	@NotBlank
	private String cep;
	@ApiModelProperty(example = "50", dataType = "int")
	@NotNull @Min(1)
	private int numero;
	@ApiModelProperty(example = "Cidade E", dataType = "String")
	@NotBlank
	private String cidade;
	@ApiModelProperty(example = "true", dataType = "boolean")
	@NotNull
	private boolean principal;
	

}

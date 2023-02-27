package com.monteiro.gerenciador.api.model.form;

import java.time.LocalDate;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PessoaForm {
	@ApiModelProperty(example = "Gilvan", dataType = "String")
	@NotBlank
	private String nome;
	@ApiModelProperty(example = "10/10/1995", dataType = "LocalDate")
	@NotNull
	@JsonFormat(pattern="dd/MM/yyyy") 
    private LocalDate dataNascimento;


}

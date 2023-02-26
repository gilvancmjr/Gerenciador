package com.monteiro.gerenciador.api.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.monteiro.gerenciador.api.model.form.EnderecoForm;
import com.monteiro.gerenciador.domain.model.Endereco;
import com.monteiro.gerenciador.domain.model.Pessoa;

@Component
public class EnderecoFormDisassembler {

	@Autowired
	private ModelMapper modelMapper;

	public Endereco toDomainObject(EnderecoForm enderecoForm) {
		return modelMapper.map(enderecoForm, Endereco.class);
	}

	public void copyToDomainObject(EnderecoForm enderecoForm, Endereco endereco) {
		Pessoa pessoa = new Pessoa();
		endereco.setPessoa(pessoa);
		modelMapper.map(enderecoForm, endereco);
	}

}

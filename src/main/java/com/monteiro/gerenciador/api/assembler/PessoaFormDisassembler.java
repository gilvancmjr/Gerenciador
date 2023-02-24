package com.monteiro.gerenciador.api.assembler;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.monteiro.gerenciador.api.model.form.PessoaForm;
import com.monteiro.gerenciador.domain.model.Endereco;
import com.monteiro.gerenciador.domain.model.Pessoa;

@Component
public class PessoaFormDisassembler {

	@Autowired
	private ModelMapper modelMapper;

	public Pessoa toDomainObject(PessoaForm pessoaForm) {
		return modelMapper.map(pessoaForm, Pessoa.class);
	}

	public void copyToDomainObject(PessoaForm pessoaForm, Pessoa pessoa) {
		List<Endereco> pessoas = new ArrayList<>();
		pessoa.setEnderecos(pessoas);
		modelMapper.map(pessoaForm, pessoa);
	}

}

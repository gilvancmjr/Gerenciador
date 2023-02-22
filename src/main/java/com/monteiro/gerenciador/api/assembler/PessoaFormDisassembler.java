package com.monteiro.gerenciador.api.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.monteiro.gerenciador.api.model.form.PessoaForm;
import com.monteiro.gerenciador.domain.model.Pessoa;

@Component
public class PessoaFormDisassembler {

	@Autowired
	private ModelMapper modelMapper;

	public Pessoa toDomainObject(PessoaForm pessoaForm) {
		return modelMapper.map(pessoaForm, Pessoa.class);
	}

	public void copyToDomainObject(PessoaForm pessoaForm, Pessoa pessoa) {
		// Para evitar org.hibernate.HibernateException: identifier of an instance of
		// com.algaworks.algafood.domain.model.Estado was altered from 1 to 2
		// pessoa.en(new Endereco());
		modelMapper.map(pessoaForm, pessoa);
	}

}

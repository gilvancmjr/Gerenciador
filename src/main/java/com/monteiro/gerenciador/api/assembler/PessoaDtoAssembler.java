package com.monteiro.gerenciador.api.assembler;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.monteiro.gerenciador.api.model.PessoaDto;
import com.monteiro.gerenciador.domain.model.Pessoa;

@Component
public class PessoaDtoAssembler {

	@Autowired
	private ModelMapper modelMapper;

	public PessoaDto toModel(Pessoa pessoa) {
		return modelMapper.map(pessoa, PessoaDto.class);
	}

	public List<PessoaDto> toCollectionModel(List<Pessoa> pessoas) {
		return pessoas.stream().map(pessoa -> toModel(pessoa)).collect(Collectors.toList());
	}

}

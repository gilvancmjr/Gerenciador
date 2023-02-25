package com.monteiro.gerenciador.api.assembler;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.monteiro.gerenciador.api.model.EnderecoDto;
import com.monteiro.gerenciador.domain.model.Endereco;

@Component
public class EnderecoAssembler {

	@Autowired
	private ModelMapper modelMapper;

	public EnderecoDto toModel(Endereco endereco) {
		return modelMapper.map(endereco, EnderecoDto.class);
	}

	public List<EnderecoDto> toCollectionModel(List<Endereco> enderecos) {
		return enderecos.stream().map(endereco -> toModel(endereco)).collect(Collectors.toList());
	}

}

package com.monteiro.gerenciador.domain.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.monteiro.gerenciador.domain.model.Endereco;
import com.monteiro.gerenciador.domain.model.Pessoa;
import com.monteiro.gerenciador.domain.repository.EnderecoRepository;

@Service
public class EnderecoService {

	@Autowired
	private EnderecoRepository enderecoRepository;
	
	public Endereco criarEndereco(Long pessoaId, Endereco endereco) {
		Pessoa pessoa = new Pessoa();
		pessoa.setId(pessoaId);
		endereco.setPessoa(pessoa);
		return enderecoRepository.save(endereco);
	}

	public List<Endereco> listarEnderecos(Long pessoaId) {
		return enderecoRepository.findByPessoaId(pessoaId);
	}

	public void definirEnderecoPrincipal(Long pessoaId, Long enderecoId) {
		List<Endereco> enderecos = enderecoRepository.findByPessoaId(pessoaId);
		enderecos.forEach(endereco -> {
			endereco.setPrincipal(endereco.getId().equals(enderecoId));
			enderecoRepository.save(endereco);
		});
	}
}

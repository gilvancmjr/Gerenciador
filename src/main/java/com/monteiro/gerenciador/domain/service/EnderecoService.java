package com.monteiro.gerenciador.domain.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.monteiro.gerenciador.domain.exception.EnderecoNaoEncontradoException;
import com.monteiro.gerenciador.domain.exception.PessoaNaoEncontradoException;
import com.monteiro.gerenciador.domain.model.Endereco;
import com.monteiro.gerenciador.domain.model.Pessoa;
import com.monteiro.gerenciador.domain.repository.EnderecoRepository;

@Service
public class EnderecoService {

	@Autowired
	private EnderecoRepository enderecoRepository;

	@Autowired
	private PessoaService pessoaService;

	public Endereco criarEndereco(Long pessoaId, Endereco endereco) {
		Pessoa pessoa = pessoaService.buscarPessoa(pessoaId);
		identificaEnderecoPrincipalTrueOuFalse(pessoaId, endereco);
		endereco.setPessoa(pessoa);
		return enderecoRepository.save(endereco);
	}

	public List<Endereco> listarEnderecos(Long pessoaId) {
		return buscarOuFalar(pessoaId);
	}

	public void definirEnderecoPrincipal(Long pessoaId, Long enderecoId) {
		List<Endereco> enderecos = buscarOuFalar(pessoaId);
		enderecoBuscarOuFalhar(enderecoId);
		enderecos.forEach(endereco -> {
			endereco.setPrincipal(endereco.getId().equals(enderecoId));
			enderecoRepository.save(endereco);
		});
	}

	private List<Endereco> buscarOuFalar(Long pessoaId) {
		List<Endereco> enderecos = enderecoRepository.findByPessoaId(pessoaId);
		if (enderecos == null || enderecos.isEmpty()) {
			throw new PessoaNaoEncontradoException(pessoaId);
		}
		return enderecos;
	}

	private void identificaEnderecoPrincipalTrueOuFalse(Long pessoaId, Endereco endereco) {
		if (endereco.isPrincipal()) {
			definirEnderecoPrincipal(pessoaId, endereco.getId());
		}
	}

	private Endereco enderecoBuscarOuFalhar(Long enderecoId) {
		return enderecoRepository.findById(enderecoId)
				.orElseThrow(() -> new EnderecoNaoEncontradoException(enderecoId));
	}
}

package com.monteiro.gerenciador.domain.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.monteiro.gerenciador.domain.exception.PessoaNaoEncontradoException;
import com.monteiro.gerenciador.domain.model.Endereco;
import com.monteiro.gerenciador.domain.model.Pessoa;
import com.monteiro.gerenciador.domain.repository.EnderecoRepository;
import com.monteiro.gerenciador.domain.repository.PessoaRepository;

@Service
public class PessoaService {

	private static final String NAO_FOI_ENCONTRADO_DADOS_ = "Não foi encontrado dados para a consulta";
	@Autowired
	private PessoaRepository pessoaRepository;
	@Autowired
	private EnderecoRepository enderecoRepository;

	public List<Pessoa> getListPessoas() {
		try {
			List<Pessoa> pessoas = pessoaRepository.findAll();
			if (pessoas.isEmpty()) {
				throw new PessoaNaoEncontradoException(NAO_FOI_ENCONTRADO_DADOS_);
			}
			return pessoas;

		} catch (PessoaNaoEncontradoException e) {
			e.printStackTrace();
			throw new PessoaNaoEncontradoException(NAO_FOI_ENCONTRADO_DADOS_);
		}

	}

	public void salvarPessoa(Pessoa pessoa) {
		pessoaRepository.save(pessoa);
	}

//	public void adicionarEndereco(Long pessoaId, Endereco endereco) {
//		Pessoa pessoa = pessoaRepository.findById(pessoaId).get();
//		pessoa.adicionarEndereco(endereco);
//		pessoaRepository.save(pessoa);
//	}

	public void definirEnderecoPrincipal(Long pessoaId, Endereco endereco) {
		Pessoa pessoa = pessoaRepository.findById(pessoaId).get();
		pessoa.setEnderecoPrincipal(endereco);
		pessoaRepository.save(pessoa);
	}

	public Pessoa getPessoa(Long id) {
		return buscarOuFalhar(id);
	}

	public Pessoa criarPessoa(Pessoa pessoa) {

		Pessoa result = pessoaRepository.save(pessoa);

		return result;

	}

	public Pessoa atualizarPessoa(Long id, Pessoa pessoa) {
		Pessoa pessoaExistente = buscarOuFalhar(id);
		if (pessoaExistente != null) {
			pessoa.setId(id);
			return pessoaRepository.save(pessoa);
		} else {
			return null;
		}
	}

	public void deletarPessoa(Long id) {
		pessoaRepository.deleteById(id);
	}

	public Pessoa buscarOuFalhar(Long pessoaId) {
		return pessoaRepository.findById(pessoaId).orElseThrow(() -> new PessoaNaoEncontradoException(pessoaId));
	}

}

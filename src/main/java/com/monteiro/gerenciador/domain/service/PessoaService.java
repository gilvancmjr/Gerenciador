package com.monteiro.gerenciador.domain.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.monteiro.gerenciador.domain.exception.PessoaNaoEncontradoException;
import com.monteiro.gerenciador.domain.model.Pessoa;
import com.monteiro.gerenciador.domain.repository.PessoaRepository;

@Service
public class PessoaService {

	@Autowired
	private PessoaRepository pessoaRepository;

	public Pessoa criarPessoa(Pessoa pessoa) {
		return pessoaRepository.save(pessoa);
	}
	
	@Transactional
	public Pessoa atualizarPessoa(Long pessoaId, Pessoa pessoaAtualizada) {
		Pessoa pessoaExistente = buscarPessoa(pessoaId);
		pessoaExistente.setNome(pessoaAtualizada.getNome());
		pessoaExistente.setDataNascimento(pessoaAtualizada.getDataNascimento());
		pessoaExistente.setEnderecos(pessoaAtualizada.getEnderecos());
		return pessoaRepository.save(pessoaExistente);
	}

	public Pessoa buscarPessoa(Long pessoaId) {
		return pessoaRepository.findById(pessoaId).orElseThrow(() -> new PessoaNaoEncontradoException(pessoaId));
	}

	public List<Pessoa> listarPessoas() {
		return pessoaRepository.findAll();
	}

}

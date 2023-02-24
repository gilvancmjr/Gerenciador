package com.monteiro.gerenciador.domain.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.monteiro.gerenciador.domain.exception.EntidadeNaoEncontradaException;
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

	public void definirEnderecoPrincipal(Long pessoaId, Endereco endereco) {
		Pessoa pessoa = pessoaRepository.findById(pessoaId).get();
		pessoa.setEnderecoPrincipal(endereco);
		pessoaRepository.save(pessoa);
	}

	public Pessoa getPessoa(Long id) {
		return buscarOuFalhar(id);
	}

	@Transactional
	public Pessoa criarPessoa(Pessoa pessoa) {
		Pessoa novaPessoa = pessoaRepository.save(pessoa);
		for (Endereco endereco : novaPessoa.getEnderecos()) {
			endereco.setPessoa(novaPessoa);
		}
		enderecoRepository.saveAll(novaPessoa.getEnderecos());
		if (novaPessoa.getEnderecoPrincipal() != null) {
			novaPessoa.getEnderecoPrincipal().setPessoa(novaPessoa);
			enderecoRepository.save(novaPessoa.getEnderecoPrincipal());
		}
		return novaPessoa;
	}

	public Pessoa atualizarPessoa(Long id, Pessoa pessoaAtualizada) {
        Pessoa pessoaExistente = pessoaRepository.findById(id)
            .orElseThrow(() -> new EntidadeNaoEncontradaException("Pessoa não encontrada com id: " + pessoaAtualizada.getId()));

        pessoaExistente.setNome(pessoaAtualizada.getNome());
        pessoaExistente.setDataDeNascimento(pessoaAtualizada.getDataDeNascimento());

        // Atualiza os endereços existentes
        for (Endereco endereco : pessoaAtualizada.getEnderecos()) {
            Endereco enderecoExistente = enderecoRepository.findById(endereco.getId())
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Endereço não encontrado com id: " + endereco.getId()));

            enderecoExistente.setLogradouro(endereco.getLogradouro());
            enderecoExistente.setCep(endereco.getCep());
            enderecoExistente.setNumero(endereco.getNumero());
            enderecoExistente.setCidade(endereco.getCidade());
        }

        // Atualiza o endereço principal existente
        Endereco enderecoPrincipal = pessoaAtualizada.getEnderecoPrincipal();
        if (enderecoPrincipal != null) {
            Endereco enderecoPrincipalExistente = enderecoRepository.findById(enderecoPrincipal.getId())
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Endereço não encontrado com id: " + enderecoPrincipal.getId()));

            pessoaExistente.setEnderecoPrincipal(enderecoPrincipalExistente);
        }

        return pessoaRepository.save(pessoaExistente);
    }


	public void deletarPessoa(Long id) {
		pessoaRepository.deleteById(id);
	}

	public Pessoa buscarOuFalhar(Long pessoaId) {
		return pessoaRepository.findById(pessoaId).orElseThrow(() -> new PessoaNaoEncontradoException(pessoaId));
	}

}

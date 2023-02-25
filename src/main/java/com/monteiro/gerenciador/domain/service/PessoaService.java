package com.monteiro.gerenciador.domain.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import com.monteiro.gerenciador.domain.model.Pessoa;
import com.monteiro.gerenciador.domain.repository.PessoaRepository;

@Service
public class PessoaService {

    @Autowired
    private PessoaRepository pessoaRepository;

    public Pessoa criarPessoa(Pessoa pessoa) {
        return pessoaRepository.save(pessoa);
    }

    public Pessoa atualizarPessoa(Long id, Pessoa pessoaAtualizada) {
        Pessoa pessoaExistente = pessoaRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Pessoa não encontrada"));
        pessoaExistente.setNome(pessoaAtualizada.getNome());
        pessoaExistente.setDataNascimento(pessoaAtualizada.getDataNascimento());
        pessoaExistente.setEnderecos(pessoaAtualizada.getEnderecos());
        return pessoaRepository.save(pessoaExistente);
    }

    public Pessoa buscarPessoa(Long id) {
        return pessoaRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Pessoa não encontrada"));
    }

    public List<Pessoa> listarPessoas() {
        return pessoaRepository.findAll();
    }
}

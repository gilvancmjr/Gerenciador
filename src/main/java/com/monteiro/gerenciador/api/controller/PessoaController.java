package com.monteiro.gerenciador.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.monteiro.gerenciador.api.assembler.PessoaDtoAssembler;
import com.monteiro.gerenciador.api.assembler.PessoaFormDisassembler;
import com.monteiro.gerenciador.api.model.PessoaDto;
import com.monteiro.gerenciador.api.model.form.PessoaForm;
import com.monteiro.gerenciador.domain.model.Pessoa;
import com.monteiro.gerenciador.domain.service.PessoaService;

import jakarta.validation.Valid;


@RestController
@RequestMapping("/pessoas")
public class PessoaController {

	@Autowired
	private PessoaService pessoaService;
	
	@Autowired
	private PessoaDtoAssembler pessoaAssembler;

	@Autowired
	private PessoaFormDisassembler pessoaFormDisassembler;

	@PostMapping
	public ResponseEntity<PessoaDto> criarPessoa(@RequestBody @Valid PessoaForm pessoaForm) {
		Pessoa pessoa = pessoaFormDisassembler.toDomainObject(pessoaForm);
		Pessoa pessoaCriada = pessoaService.criarPessoa(pessoa);
		PessoaDto pessoaDto = pessoaAssembler.toModel(pessoaCriada);
		return ResponseEntity.status(HttpStatus.CREATED).body(pessoaDto);
	}

	@PutMapping("/{pessoaId}")
	public ResponseEntity<PessoaDto> atualizarPessoa(@PathVariable Long pessoaId, @RequestBody @Valid PessoaForm pessoaForm) {
		Pessoa pessoa = pessoaFormDisassembler.toDomainObject(pessoaForm);
		Pessoa pessoaAtualizada = pessoaService.atualizarPessoa(pessoaId, pessoa);
		PessoaDto pessoaDto = pessoaAssembler.toModel(pessoaAtualizada);
		return ResponseEntity.ok(pessoaDto);
	}

	@GetMapping("/{pessoaId}")
	public ResponseEntity<PessoaDto> buscarPessoa(@PathVariable Long pessoaId) {
		Pessoa pessoa = pessoaService.buscarPessoa(pessoaId);
		PessoaDto pessoaDto = pessoaAssembler.toModel(pessoa);
		return ResponseEntity.ok(pessoaDto);
	}

	@GetMapping
	public ResponseEntity<List<PessoaDto>> listar() {
		List<Pessoa> pessoas = pessoaService.listarPessoas();
		List<PessoaDto> pessoaDtos = pessoaAssembler.toCollectionModel(pessoas);
		return ResponseEntity.ok(pessoaDtos);
	}
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> removerPessoa(@PathVariable Long id) {
		 pessoaService.removerPessoa(id);
		return ResponseEntity.noContent().build();
	}

}
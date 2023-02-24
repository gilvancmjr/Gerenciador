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

import com.monteiro.gerenciador.api.assembler.PessoaAssembler;
import com.monteiro.gerenciador.api.assembler.PessoaFormDisassembler;
import com.monteiro.gerenciador.api.model.PessoaDto;
import com.monteiro.gerenciador.api.model.form.PessoaForm;
import com.monteiro.gerenciador.domain.model.Pessoa;
import com.monteiro.gerenciador.domain.service.PessoaService;

@RestController
@RequestMapping("/pessoas")
public class PessoaController {

	@Autowired
	private PessoaService pessoaService;

	@Autowired
	private PessoaAssembler pessoaAssembler;
	
	@Autowired
	private PessoaFormDisassembler pessoaFormDisassembler;

	@GetMapping
	public ResponseEntity<List<PessoaDto>> listar() {
		List<Pessoa> pessoas = pessoaService.getListPessoas();
		List<PessoaDto> pessoaDtos = pessoaAssembler.toCollectionModel(pessoas);
		return ResponseEntity.ok(pessoaDtos);

	}

	@GetMapping("/{pessoaId}")
	public ResponseEntity<PessoaDto> getPessoa(@PathVariable Long pessoaId) {
		Pessoa pessoa = pessoaService.buscarOuFalhar(pessoaId);
		PessoaDto pessoaDto = pessoaAssembler.toModel(pessoa);
		return ResponseEntity.ok(pessoaDto);
	}

	@PostMapping
	public ResponseEntity<PessoaDto> criarPessoa(@RequestBody PessoaForm pessoa) {
		System.out.println("Pessoa: "+pessoa);
		Pessoa pessoaAtual = pessoaFormDisassembler.toDomainObject(pessoa);
		Pessoa novaPessoa = pessoaService.criarPessoa(pessoaAtual);
		PessoaDto pessoaDto = pessoaAssembler.toModel(novaPessoa);
		return ResponseEntity.status(HttpStatus.CREATED).body(pessoaDto);
	}

	@PutMapping("/{id}")
	public ResponseEntity<PessoaDto> atualizarPessoa(@PathVariable Long id, @RequestBody Pessoa pessoa) {
		Pessoa pessoaExistente = pessoaService.getPessoa(id);
		if (pessoaExistente != null) {
			Pessoa pessoaAtualizada = pessoaService.atualizarPessoa(id, pessoa);
			PessoaDto pessoaDto = pessoaAssembler.toModel(pessoaAtualizada);
			return ResponseEntity.ok(pessoaDto);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deletarPessoa(@PathVariable Long id) {
		Pessoa pessoaExistente = pessoaService.getPessoa(id);
		if (pessoaExistente != null) {
			pessoaService.deletarPessoa(id);
			return ResponseEntity.noContent().build();
		} else {
			return ResponseEntity.notFound().build();
		}
	}
}

package com.monteiro.gerenciador.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.monteiro.gerenciador.api.assembler.EnderecoAssembler;
import com.monteiro.gerenciador.api.model.EnderecoDto;
import com.monteiro.gerenciador.domain.model.Endereco;
import com.monteiro.gerenciador.domain.service.EnderecoService;

@RestController
@RequestMapping("/pessoas/{pessoaId}/enderecos")
public class EnderecoController {

    @Autowired
    private EnderecoService enderecoService;
    @Autowired
    private EnderecoAssembler enderecoAssembler;

    @PostMapping
    public ResponseEntity<EnderecoDto> criarEndereco(@PathVariable Long pessoaId, @RequestBody Endereco endereco) {
        Endereco enderecoCriado = enderecoService.criarEndereco(pessoaId, endereco);
        EnderecoDto enderecoDto = enderecoAssembler.toModel(enderecoCriado);
        return ResponseEntity.status(HttpStatus.CREATED).body(enderecoDto);
    }

    @GetMapping
    public ResponseEntity<List<EnderecoDto>> listarEnderecos(@PathVariable Long pessoaId) {
        List<Endereco> enderecos = enderecoService.listarEnderecos(pessoaId);
        List<EnderecoDto> enderecoDtos = enderecoAssembler.toCollectionModel(enderecos);
        return ResponseEntity.ok(enderecoDtos);
    }

    @PutMapping("/{enderecoId}/principal")
    public ResponseEntity<Void> definirEnderecoPrincipal(@PathVariable Long pessoaId, @PathVariable Long enderecoId) {
        enderecoService.definirEnderecoPrincipal(pessoaId, enderecoId);
        return ResponseEntity.noContent().build();
    }
}

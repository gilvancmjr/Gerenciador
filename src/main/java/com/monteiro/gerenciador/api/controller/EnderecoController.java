package com.monteiro.gerenciador.api.controller;

import java.util.List;

import javax.validation.Valid;

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

import com.monteiro.gerenciador.api.assembler.EnderecoDtoAssembler;
import com.monteiro.gerenciador.api.assembler.EnderecoFormDisassembler;
import com.monteiro.gerenciador.api.model.EnderecoDto;
import com.monteiro.gerenciador.api.model.PessoaDto;
import com.monteiro.gerenciador.api.model.form.EnderecoForm;
import com.monteiro.gerenciador.domain.model.Endereco;
import com.monteiro.gerenciador.domain.service.EnderecoService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/pessoas/{pessoaId}/enderecos")
public class EnderecoController {

    @Autowired
    private EnderecoService enderecoService;
    @Autowired
    private EnderecoDtoAssembler enderecoAssembler;
    @Autowired
    private EnderecoFormDisassembler enderecoFormDisassembler;

    @ApiOperation(value = "Criar Endereço", notes = "Permite a realizar da criação de um endereço para uma pessoa, "
    		+ "com os parametro logradouro, cep, numero, cidade e principal")
	@ApiResponses(value = { @ApiResponse(code = 201, message = "Created", response = EnderecoDto.class) })
    @PostMapping
    public ResponseEntity<EnderecoDto> criarEndereco(@PathVariable Long pessoaId, @RequestBody @Valid EnderecoForm enderecoForm) {
    	Endereco  endereco = enderecoFormDisassembler.toDomainObject(enderecoForm);
        Endereco enderecoCriado = enderecoService.criarEndereco(pessoaId, endereco);
        EnderecoDto enderecoDto = enderecoAssembler.toModel(enderecoCriado);
        return ResponseEntity.status(HttpStatus.CREATED).body(enderecoDto);
    }

    @ApiOperation(value = "Consulta Endereços", notes = "Permite a realizar a consulta de uma lista de endereços")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success", response = PessoaDto.class) })
    @GetMapping
    public ResponseEntity<List<EnderecoDto>> listarEnderecos(@PathVariable Long pessoaId) {
        List<Endereco> enderecos = enderecoService.listarEnderecos(pessoaId);
        List<EnderecoDto> enderecoDtos = enderecoAssembler.toCollectionModel(enderecos);
        return ResponseEntity.ok(enderecoDtos);
    }
    @ApiOperation(value = "Define Endereço Principal", notes = "Permite definir um endereço como principal")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success", response = PessoaDto.class) })
    @PutMapping("/{enderecoId}/principal")
    public ResponseEntity<Void> definirEnderecoPrincipal(@PathVariable Long pessoaId, @PathVariable Long enderecoId) {
        enderecoService.definirEnderecoPrincipal(pessoaId, enderecoId);
        return ResponseEntity.noContent().build();
    }
}

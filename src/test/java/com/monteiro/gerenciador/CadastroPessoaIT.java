package com.monteiro.gerenciador;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringRunner;

import com.monteiro.gerenciador.util.ResourceUtils;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CadastroPessoaIT {

	
	private static final int PESSOA_ID_INEXISTENTE = 100;
	private static final int PESSOA_ID_ATUALIZAR = 2;
	private static final int PESSOA_ID = 1;
	
	private static final String DADOS_INVALIDOS_PROBLEM_TITLE = "Dados inválidos";
	private static final String RECURSO_NAO_ENCONTRADO = "Recurso não encontrada";
	
	@LocalServerPort
	private int port;
	private String jsonCorretoPessoa;
	private String jsonIncorretoPessoa;

	

	
	@BeforeEach
	public void setUp() {
		RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
	    RestAssured.port = port;
	    RestAssured.basePath = "/pessoas";
	    
	    jsonCorretoPessoa = ResourceUtils.getContentFromResource("/json/correto/pessoa.json");
	    jsonIncorretoPessoa = ResourceUtils.getContentFromResource("/json/incorreto/pessoa-sem-nome.json");
	}

	@Test
	public void deveRetornarStatus200_QuandoConsultarPessoas() {
		RestAssured.given()
			.accept(ContentType.JSON)
		.when()
			.get()
		.then()
			.statusCode(HttpStatus.OK.value());
	}
	
	@Test
	public void deveRetornarStatus200eNomePessoa_QuandoConsultarPessoa() {
		RestAssured.given()
			.pathParam("pessoaId", PESSOA_ID)
			.accept(ContentType.JSON)
		.when()
			.get("/{pessoaId}")
		.then()
			.statusCode(HttpStatus.OK.value())
			.body("nome", Matchers.equalTo("João"));
	}
	
	@Test
	public void deveRetornarStatus201_QuandoCadastrarPessoa() {
		RestAssured.given()
	        .body(jsonCorretoPessoa)
	        .contentType(ContentType.JSON)
	        .accept(ContentType.JSON)
	    .when()
	        .post()
	    .then()
	        .statusCode(HttpStatus.CREATED.value());
		
	}
	
	@Test
	public void deveRetornarStatus200_QuandoAtualizarPessoa() {
		RestAssured.given()
			.pathParam("pessoaId", PESSOA_ID_ATUALIZAR)
	        .body(jsonCorretoPessoa)
	        .contentType(ContentType.JSON)
	        .accept(ContentType.JSON)
	    .when()
	        .put("/{pessoaId}")
	    .then()
	        .statusCode(HttpStatus.OK.value());
	}
	
	@Test
	public void deveRetornarStatus404_QuandoConsultarPessoaInexistente() {
	RestAssured.given()
		.pathParam("pessoaId", PESSOA_ID_INEXISTENTE)
		.accept(ContentType.JSON)
	.when()
		.get("/{pessoaId}")
	.then()
		.statusCode(HttpStatus.NOT_FOUND.value())
		.body("title", Matchers.equalTo(RECURSO_NAO_ENCONTRADO));
	}
	
	@Test
	public void deveRetornarStatus400_QuandoCadastrarPessoaSemNome() {
	RestAssured.given()
        .body(jsonIncorretoPessoa)
        .contentType(ContentType.JSON)
        .accept(ContentType.JSON)
    .when()
        .post()
    .then()
        .statusCode(HttpStatus.BAD_REQUEST.value())
        .body("title", Matchers.equalTo(DADOS_INVALIDOS_PROBLEM_TITLE));
	}
	
}

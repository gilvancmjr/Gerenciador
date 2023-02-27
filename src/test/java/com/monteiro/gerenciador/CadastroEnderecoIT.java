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
public class CadastroEnderecoIT {
	
	
	
	@LocalServerPort
	private int port;
	
	String baseApiUrl = "/pessoas";
	private String jsonCorretoEndereco;
	private String jsonCorretoEnderecoSemLogradouro;
	
	private static final String DADOS_INVALIDOS_PROBLEM_TITLE = "Dados inválidos";
	private static final String RECURSO_NAO_ENCONTRADO = "Recurso não encontrada";
	
	
	@BeforeEach
	public void setUp() {
		RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
	    RestAssured.port = port;
	    RestAssured.basePath = "/pessoas/1/enderecos";
	    
	    jsonCorretoEndereco = ResourceUtils.getContentFromResource("/json/correto/endereco.json");
	    jsonCorretoEnderecoSemLogradouro = ResourceUtils
	    		.getContentFromResource("/json/incorreto/endereco_sem_logradouro.json");
	}
	
	@Test
	public void deveRetornarStatus200_QuandoConsultarEndereços() {
		RestAssured.given()
			.accept(ContentType.JSON)
		.when()
			.get()
		.then()
			.statusCode(HttpStatus.OK.value());
	}
	
	@Test
	public void deveRetornarStatus204_QuandoAlterarEndereçoPrincipal() {
		RestAssured.given()
			.pathParam("enderecoId", 1)
			
			.accept(ContentType.JSON)
		.when()
			.put("/{enderecoId}/principal")
		.then()
			.statusCode(HttpStatus.NO_CONTENT.value());
	}
	
	@Test
	public void deveRetornarStatus201_QuandoCadastrarEndereço() {
		RestAssured.given()
	        .body(jsonCorretoEndereco)
	        .contentType(ContentType.JSON)
	        .accept(ContentType.JSON)
	    .when()
	        .post()
	    .then()
	        .statusCode(HttpStatus.CREATED.value());
		
	}
	
	@Test
	public void deveRetornarStatus400_QuandoCadastrarEnderecoSemLogradouro() {
		RestAssured.given()
	        .body(jsonCorretoEnderecoSemLogradouro)
	        .contentType(ContentType.JSON)
	        .accept(ContentType.JSON)
	    .when()
	        .post()
	    .then()
	        .statusCode(HttpStatus.BAD_REQUEST.value())
	        .body("title", Matchers.equalTo(DADOS_INVALIDOS_PROBLEM_TITLE));
		
	}
	
	@Test
	public void deveRetornarStatus404_QuandoAlterarEndereçoPrincipalInexistente() {
		RestAssured.given()
		.pathParam("enderecoId", 100)
		
		.accept(ContentType.JSON)
	.when()
		.put("/{enderecoId}/principal")
	.then()
		.statusCode(HttpStatus.NOT_FOUND.value())
		.body("title", Matchers.equalTo(RECURSO_NAO_ENCONTRADO));
	}

}

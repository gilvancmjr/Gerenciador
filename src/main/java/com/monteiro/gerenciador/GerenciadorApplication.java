package com.monteiro.gerenciador;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@EnableWebMvc
@SpringBootApplication
public class GerenciadorApplication {

	public static void main(String[] args) {
		SpringApplication.run(GerenciadorApplication.class, args);
	}

}

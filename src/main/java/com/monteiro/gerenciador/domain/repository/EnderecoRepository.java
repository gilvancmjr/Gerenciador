package com.monteiro.gerenciador.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.monteiro.gerenciador.domain.model.Endereco;

public interface EnderecoRepository extends JpaRepository<Endereco, Long>{
	
	///Endereco findById(Long id);

}

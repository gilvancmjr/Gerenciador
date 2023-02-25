package com.monteiro.gerenciador.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.monteiro.gerenciador.domain.model.Endereco;

@Repository
public interface EnderecoRepository extends JpaRepository<Endereco, Long>{
	
	List<Endereco> findByPessoaId(Long pessoaId);

}

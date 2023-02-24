package com.monteiro.gerenciador.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.monteiro.gerenciador.domain.model.Pessoa;

@Repository
public interface PessoaRepository  extends JpaRepository<Pessoa, Long>{
	
//	@Query("from Pessoa")
//	List<Pessoa> findByEnderecosId(Long id);

    
}

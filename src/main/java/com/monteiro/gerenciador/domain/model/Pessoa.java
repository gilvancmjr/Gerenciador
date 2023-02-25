package com.monteiro.gerenciador.domain.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Pessoa {

	@EqualsAndHashCode.Include
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	private String nome;
	@JsonFormat(pattern="dd/MM/yyyy") 
    private LocalDate dataNascimento;
    @OneToMany(mappedBy = "pessoa", cascade = CascadeType.ALL)
    private List<Endereco> enderecos = new ArrayList<>();


	// public void adicionarEndereco(Endereco endereco) {
	// this.enderecos.add(endereco);
	// }
	//
	// public void removerEndereco(Endereco endereco) {
	// this.enderecos.remove(endereco);
	// }
	//
	// public Endereco getEnderecoPrincipal() {
	// return enderecoPrincipal;
	// }
	//
	// public void setEnderecoPrincipal(Endereco enderecoPrincipal) {
	// this.enderecoPrincipal = enderecoPrincipal;
	// }

}

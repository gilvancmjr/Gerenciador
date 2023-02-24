package com.monteiro.gerenciador.domain.model;

import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Pessoa {

	@EqualsAndHashCode.Include
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "nome")
	private String nome;

	@JsonFormat(pattern = "dd/MM/yyyy")
	@Column(name = "data_de_nascimento")
	private LocalDate dataDeNascimento;

	@OneToMany(mappedBy = "pessoa", cascade = CascadeType.ALL)
	private List<Endereco> enderecos;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "endereco_principal_id", referencedColumnName = "id")
	private Endereco enderecoPrincipal;

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

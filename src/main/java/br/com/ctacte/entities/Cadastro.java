package br.com.ctacte.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name="cadastro", schema="apl_cadastro")
public class Cadastro implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "IDCADASTRO", columnDefinition = "NUMBER(9)")
	private Long idcadastro;
	
	@Column(name = "TIPOCADASTRO", columnDefinition = "NUMBER(1)")
	private Long tipocadastro;
	
	@Column(name = "NUMERO", columnDefinition = "VARCHAR2(20)")
	private String numero;
	
	@Column(name = "TIPO", columnDefinition = "NUMBER(1)")
	private Long tipo;
	
	@Column(name = "NOME", columnDefinition = "VARCHAR2(200)")
	private String nome;
	
	@Column(name = "IDMUNICIPIO", columnDefinition = "NUMBER(6)")
	private Long idmunicipio;
	
	@JsonIgnore
	@OneToMany(mappedBy = "idcadastro")
	private List<ContaCorrente> conta = new ArrayList<>();

}

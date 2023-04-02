package br.com.ctacte.entities;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tributo", schema="apl_regrascalculo")
public class Tributo {
	
	

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "IDTRIBUTO", columnDefinition = "NUMBER")
	private Long idtributo;
	
	@Column(name = "DESCRICAOSIMPLIFICADA", columnDefinition = "VARCHAR2")
	private String descricao;
	
	@Column(name = "TIPOTRIBUTO", columnDefinition = "NUMBER")
	private Long tipoTributo;
	
	@Column(name = "SIGLA", columnDefinition = "VARCHAR2(25)")
	private String sigla;
	
	@Column(name = "DTINICIO")
	@Temporal(TemporalType.DATE)
	private Date dtInicio;
	
	@Column(name = "DTFIM")
	@Temporal(TemporalType.DATE)
	private Date dtFim;
	
	@JsonIgnore
	@OneToMany(mappedBy = "idtributo")
	private List<ContaCorrente> conta = new ArrayList<>();

}

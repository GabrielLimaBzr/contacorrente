package br.com.ctacte.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
@Table(name = "ctacte", schema="apl_ctacte")
public class ContaCorrente implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "IDCTACTE", columnDefinition = "NUMBER")
	private Long idctacte;
	
	@Column(name = "NUMDOCUMENTO", columnDefinition = "NUMBER")
	private BigDecimal numDoc;
	
	@Column(name = "PERIODOREF", columnDefinition = "NUMBER")
	private BigDecimal periodoRef;
	
	@Column(name = "NUMPARCELA", columnDefinition = "NUMBER")
	private BigDecimal numParcela;
	
	@Column(name = "ESTADOCONTA", columnDefinition = "NUMBER")
	private BigDecimal codEstadoConta;
	
	@Column(name = "DTVCTO")
	@Temporal(TemporalType.DATE)
	private Date  dtVencimento;
	
	@Column(name = "DTCRIACAO")
	@Temporal(TemporalType.DATE)
	private Date  dtGeracao;
	
	private Long cancelada;
	
	private Long baixamanual;
	
	@Column(name = "VALDEBINIMOE", columnDefinition = "NUMBER(17,2)")
	private BigDecimal  valorOrigem;
	
	@Column(name = "VALDEBATUMOE", columnDefinition = "NUMBER(17,2)")
	private BigDecimal  valorCorrecao;
	
	@Column(name = "VALSALDOMULTAMOE", columnDefinition = "NUMBER(17,2)")
	private BigDecimal  valorMulta;
	
	@Column(name = "VALSALDOJUROSMOE", columnDefinition = "NUMBER(17,2)")
	private BigDecimal  valorJuros;
	
	@Column(name = "VALDESCONTOMOE", columnDefinition = "NUMBER(17,2)")
	private BigDecimal  valorDesconto;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="IDPESSOA", columnDefinition = "NUMBER(8)")
	private Cadastro idcadastro; 
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="IDTRIBUTO", columnDefinition = "NUMBER(5)")
	private Tributo idtributo;

}

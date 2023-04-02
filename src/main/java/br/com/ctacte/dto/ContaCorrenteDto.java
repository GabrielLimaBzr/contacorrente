package br.com.ctacte.dto;

import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
public class ContaCorrenteDto {

	private Long idctacte;
	private BigDecimal numDoc;
	private BigDecimal periodoRef;
	private BigDecimal numParcela;
	private BigDecimal codEstadoConta;
	@JsonFormat(pattern = "dd/MM/yyyy")
	private Date dtVencimento;
	@JsonFormat(pattern = "dd/MM/yyyy")
	private Date dtGeracao;
	private Long cancelada;
	private Long baixamanual;

	private BigDecimal valorOrigem;
	private BigDecimal valorCorrecao;
	private BigDecimal valorMulta;
	private BigDecimal valorJuros;
	private BigDecimal valorDesconto;
	private Long idcadastro;
	private Long idtributo;

	


}

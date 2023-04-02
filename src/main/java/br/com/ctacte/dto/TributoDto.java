package br.com.ctacte.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;


@Data
public class TributoDto {
	
	private Long idtributo;
	
	private String descricao;
	
	private Long tipoTributo;
	
	private String sigla;
	
	@JsonFormat(pattern = "dd/MM/yyyy")
	private Date dtInicio;
	
	@JsonFormat(pattern = "dd/MM/yyyy")
	private Date dtFim;

}

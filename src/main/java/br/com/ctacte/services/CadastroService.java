package br.com.ctacte.services;

import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import br.com.ctacte.dto.CadastroDto;
import br.com.ctacte.entities.Cadastro;
import br.com.ctacte.exceptions.ObjectNotFoundException;
import br.com.ctacte.repositories.CadastroRepository;

@Service
public class CadastroService {

	@Autowired
	private CadastroRepository repository;
	
	@Autowired
	private ModelMapper model;

	public CadastroDto findById(Long id){
		Optional<Cadastro> obj = repository.findById(id);
		if(obj.isEmpty()) {
			throw new ObjectNotFoundException("Cadastro não encontrada! ID\" + id");
		}
		return model.map(obj, CadastroDto.class);
	}

	public Page<CadastroDto> consultaCadastro(CadastroDto filtro, PageRequest page) {
		String nome = filtro.getNome() != null && !filtro.getNome().isEmpty() ? filtro.getNome() : null;
		String numero = filtro.getNumero() != null && !filtro.getNumero().isEmpty() ? filtro.getNumero() : null;
		Long tipoCad = filtro.getTipocadastro() != null && filtro.getTipocadastro() != 0 ? filtro.getTipocadastro() : null;
		Long idmunicipio =  filtro.getIdmunicipio() != null && filtro.getIdmunicipio() != 0 ? filtro.getIdmunicipio() : null;

		Page<Cadastro> obj = repository.consultarCadastro(nome, numero, tipoCad, idmunicipio, page);
		if(obj.isEmpty()) {
			throw new ObjectNotFoundException("Cadastro não encontrado! Por favor refaça sua pesquisa.");
		}
		return obj.map(x -> model.map(x, CadastroDto.class));
	}

}

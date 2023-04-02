package br.com.ctacte.services;


import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import br.com.ctacte.dto.ContaCorrenteDto;
import br.com.ctacte.entities.ContaCorrente;
import br.com.ctacte.exceptions.ObjectNotFoundException;
import br.com.ctacte.repositories.ContaCorrenteRepository;

@Service
public class ContaCorrenteService {

	@Autowired
	private ContaCorrenteRepository repository;
	
	@Autowired
	private ModelMapper model;

	public ContaCorrenteDto findById(Long id){
		Optional<ContaCorrente> obj = repository.findById(id);
		if(obj.isPresent()) {
			throw new ObjectNotFoundException("Conta não encontrada! ID" + id);
		}
		return model.map(obj, ContaCorrenteDto.class);
	}
	
	
	public 	List<ContaCorrenteDto> buscarContaCadastro(ContaCorrenteDto filtro){
		List<ContaCorrente> obj = repository.buscarContaCadastro(filtro.getIdcadastro());
		if (obj.isEmpty()) {
	        throw new ObjectNotFoundException("Nenhuma conta foi encontrada! Tente Refazer a consulta.");
	    }
	    return obj.stream().map(x -> model.map(x, ContaCorrenteDto.class)).collect(Collectors.toList());
	}
	
	public 	Page<ContaCorrenteDto> buscarContaCadastroPag(ContaCorrenteDto filtro, PageRequest page){
		Page<ContaCorrente> pagi = repository.buscarContaCadastroPag(filtro.getIdcadastro(), page);
		
		if (pagi.isEmpty()) {
	        throw new ObjectNotFoundException("Nenhuma conta foi encontrada! Tente Refazer a consulta.");
	    }
	    return pagi.map(x -> model.map(x, ContaCorrenteDto.class));
	}

}

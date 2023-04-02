package br.com.ctacte.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.ctacte.dto.CadastroDto;
import br.com.ctacte.services.CadastroService;

@RequestMapping(value = "/cadastro")
@RestController()
public class CadastroController {
	
	@Autowired
	private CadastroService service;
	
	@GetMapping(value ="/{id}")
	public ResponseEntity<CadastroDto>  findById(@PathVariable("id") Long id){
		return ResponseEntity.ok().body(service.findById(id));
	}
	
	
	@PostMapping(value="/retornaCadastro")
	public ResponseEntity<Page<CadastroDto>> consultaCadastro(
			@RequestBody CadastroDto filtro,
			@RequestParam(value="page") Integer page, 
			@RequestParam(value="size") Integer size){
		PageRequest pageRequest = PageRequest.of(page, size);
		return ResponseEntity.ok().body(service.consultaCadastro(filtro, pageRequest));
	}
	
}

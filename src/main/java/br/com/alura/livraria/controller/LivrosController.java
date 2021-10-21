package br.com.alura.livraria.controller;

import java.net.URI;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.alura.livraria.dto.LivrosDto;
import br.com.alura.livraria.dto.LivrosFormDto;
import br.com.alura.livraria.service.LivrosService;

@RestController
@RequestMapping ("/livros")
public class LivrosController {
	
	@Autowired
	private LivrosService service;
	
	@GetMapping
	public Page<LivrosDto> listar(Pageable pageable){
		return service.listar(pageable);
	}
	
	@PostMapping
	public ResponseEntity<LivrosDto> cadastrar(@RequestBody @Valid LivrosFormDto dto, UriComponentsBuilder uriBuilder) {
		LivrosDto livrosDto = service.cadastrar(dto);
		
		URI uri = uriBuilder.path("/livros/{id]").buildAndExpand(livrosDto.getId()).toUri();
		
		return ResponseEntity.created(uri).body(livrosDto);
	}

}

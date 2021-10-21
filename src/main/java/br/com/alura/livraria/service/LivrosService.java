package br.com.alura.livraria.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.alura.livraria.dto.LivrosDto;
import br.com.alura.livraria.dto.LivrosFormDto;
import br.com.alura.livraria.modelo.Livro;
import br.com.alura.livraria.repository.LivroRepository;

@Service
public class LivrosService {
	
	@Autowired
	private LivroRepository repository;
	private ModelMapper modelMapper = new ModelMapper();
	
	public Page<LivrosDto> listar(Pageable pageable){
		Page<Livro> livro = repository.findAll(pageable);
		return livro.map(l -> modelMapper.map(l, LivrosDto.class));
	}
	
	@Transactional
	public LivrosDto cadastrar(LivrosFormDto dto) {
		Livro livros = modelMapper.map(dto, Livro.class);
		
		repository.save(livros);
		
		return modelMapper.map(livros, LivrosDto.class);
	}
}

package br.com.alura.livraria.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import br.com.alura.livraria.dto.LivrosDto;
import br.com.alura.livraria.dto.LivrosFormDto;
import br.com.alura.livraria.modelo.Livro;

@Service
public class LivrosService {
	
	private List<Livro> livro = new ArrayList<>();
	private ModelMapper modelMapper = new ModelMapper();
	
	public List<LivrosDto> listar(){
		return livro.stream().map(l -> modelMapper.map(l, LivrosDto.class)).collect(Collectors.toList());
	}
	
	public void cadastrar(LivrosFormDto dto) {
		Livro livros = modelMapper.map(dto, Livro.class);
		
		livro.add(livros);
	}
}

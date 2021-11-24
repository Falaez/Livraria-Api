package br.com.alura.livraria.service;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.alura.livraria.dto.AtualizacaoLivrosFormDto;
import br.com.alura.livraria.dto.LivroDetalhadoDto;
import br.com.alura.livraria.dto.LivrosDto;
import br.com.alura.livraria.dto.LivrosFormDto;
import br.com.alura.livraria.modelo.Livro;
import br.com.alura.livraria.repository.AutorRepository;
import br.com.alura.livraria.repository.LivroRepository;

@Service
public class LivrosService {
	
	@Autowired
	private LivroRepository repository;
	@Autowired
	private AutorRepository autorRepository;
	
	private ModelMapper modelMapper = new ModelMapper();
	
	public Page<LivrosDto> listar(Pageable pageable){
		Page<Livro> livro = repository.findAll(pageable);
		return livro.map(l -> modelMapper.map(l, LivrosDto.class));
	}
	
	@Transactional
	public LivrosDto cadastrar(LivrosFormDto dto) {
		Livro livros = modelMapper.map(dto, Livro.class);
		livros.setId(null);
		repository.save(livros);
		
		return modelMapper.map(livros, LivrosDto.class);
	}

	@Transactional
	public LivrosDto atualizar(@Valid AtualizacaoLivrosFormDto dto) {
		Livro livro= repository.getById(dto.getId());
		
		livro.atualizarInformacao(dto.getTitulo(),dto.getDataLancamento(),dto.getPaginas(),autorRepository.getById(dto.getAutorId()));
		return modelMapper.map(livro, LivrosDto.class);
	}

	public void remover(@NotNull Long id) {
		repository.deleteById(id);
	}
	
	public LivroDetalhadoDto detalhar(@NotNull Long id) {
		Livro livro = repository.findById(id).orElseThrow(() -> new EntityNotFoundException());
		return modelMapper.map(livro, LivroDetalhadoDto.class);
	}
	
}

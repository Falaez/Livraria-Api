package br.com.alura.livraria.service;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.alura.livraria.dto.AutorDto;
import br.com.alura.livraria.dto.AutorFormDto;
import br.com.alura.livraria.modelo.Autor;
import br.com.alura.livraria.repository.AutorRepository;

@Service
public class AutorService {
		
		@Autowired
		private AutorRepository autorRepository;
		private ModelMapper modelMapper = new ModelMapper();
		
		public Page<AutorDto> listar(Pageable pageable){
			Page<Autor> autores = autorRepository.findAll(pageable);
			return autores.map(t -> modelMapper.map(t, AutorDto.class));
		}
		
		@Transactional
		public AutorDto cadastrar(@Valid AutorFormDto dto) {
			Autor autor = modelMapper.map(dto, Autor.class);
			
			autorRepository.save(autor);
			return modelMapper.map(autor, AutorDto.class);
		}
}

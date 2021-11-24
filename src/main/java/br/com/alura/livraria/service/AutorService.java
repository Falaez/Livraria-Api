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

import br.com.alura.livraria.dto.AtualizacaoAutorFormDto;
import br.com.alura.livraria.dto.AutorDetalhadoDto;
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
		
		@Transactional
		public AutorDto atualizar(@Valid AtualizacaoAutorFormDto dto) {
			Autor autor = autorRepository.getById(dto.getId());
			
			autor.atualizarInformacoes(dto.getNome(),dto.getEmail(),dto.getDataNascimento(),dto.getMiniCurriculo());
			return modelMapper.map(autor, AutorDto.class);
		}
		
		@Transactional
		public void remover(@NotNull Long id) {
			autorRepository.deleteById(id);
		}

		public AutorDetalhadoDto detalhar(@NotNull Long id) {
			Autor autor = autorRepository.findById(id).orElseThrow(() -> new EntityNotFoundException());
			return modelMapper.map(autor, AutorDetalhadoDto.class);
		}
}

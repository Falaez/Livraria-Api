package br.com.alura.livraria.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDate;

import javax.persistence.EntityNotFoundException;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import br.com.alura.livraria.dto.LivrosDto;
import br.com.alura.livraria.dto.LivrosFormDto;
import br.com.alura.livraria.modelo.Autor;
import br.com.alura.livraria.modelo.Livro;
import br.com.alura.livraria.repository.AutorRepository;
import br.com.alura.livraria.repository.LivroRepository;

@ExtendWith(MockitoExtension.class)
class LivroServiceTest {
	
	@Mock
	private AutorRepository autorRepository;
	
	@Mock
	private LivroRepository livroRepository;
	
	@Mock
	private ModelMapper modelMapper;
	
	@InjectMocks
	private LivrosService service;
	
	private LivrosFormDto criarLivroFormDto() {
		LivrosFormDto formDto = new LivrosFormDto(
				"titulo",
				LocalDate.of(1990, 5, 6),
				500,
				35l);
		return formDto;
	}


	    @Test
	    void deveriaCadastrarUmLivro() {

	        LivrosFormDto formDto = criarLivroFormDto();

	        Autor autor = new Autor( "Mario", "mario@email.com", LocalDate.of(1990, 5, 6), "curriculo");

	        Livro livro = new Livro( formDto.getTitulo(), formDto.getDataLancamento(),
	                formDto.getPaginas(), autor);

	        Mockito.when(modelMapper.map(formDto, Livro.class)).thenReturn(livro);


	        Mockito.when(modelMapper.map(livro, LivrosDto.class)).thenReturn(new LivrosDto(
	        		null,
	        		"titulo",
	        		LocalDate.of(1990, 5, 6),
	        		autor)
	        );


	        LivrosDto dto = service.cadastrar(formDto);

	        Mockito.verify(livroRepository).save(Mockito.any());

	        assertEquals(formDto.getTitulo(), dto.getTitulo());
	        assertEquals(formDto.getDataLancamento(), dto.getDataLancamento());

	    }


	    @Test
	    void naoDeveriaCadastrarUmLivroComAutorInexistente() {

	        LivrosFormDto formDto = criarLivroFormDto();

	        Mockito.when(autorRepository.getById(formDto.getAutorId()))
	                .thenThrow(EntityNotFoundException.class);

	        assertThrows(IllegalArgumentException.class, () -> service.cadastrar(formDto));

	    }

	 


}

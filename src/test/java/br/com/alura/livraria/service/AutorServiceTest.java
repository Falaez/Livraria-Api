package br.com.alura.livraria.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import br.com.alura.livraria.dto.AutorDto;
import br.com.alura.livraria.dto.AutorFormDto;
import br.com.alura.livraria.repository.AutorRepository;
import br.com.alura.livraria.repository.LivroRepository;

@ExtendWith(MockitoExtension.class)
class AutorServiceTest {
	
	@Mock
	private AutorRepository autorRespository;
	
	@Mock
	private LivroRepository livroRepository;
	
	@InjectMocks
	private AutorService service;
	
	private AutorFormDto criarAutorFormDto() {
		AutorFormDto formDto = new AutorFormDto(
				"Mario",
				"mario@email.com",
				LocalDate.of(1990, 5, 6),
				"curriculo");
		return formDto;
	}
	
	@Test
	void deveriaCadastrarUmAutor() {
		AutorFormDto formDto = criarAutorFormDto();
		
		
		AutorDto dto = service.cadastrar(formDto);
		
		Mockito.verify(autorRespository).save(Mockito.any());
		
		
		assertEquals(formDto.getNome(), dto.getNome());
		
	}



}

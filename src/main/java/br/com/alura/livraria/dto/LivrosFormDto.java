package br.com.alura.livraria.dto;

import java.time.LocalDate;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;

import br.com.alura.livraria.modelo.Autor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LivrosFormDto {
	
	@NotBlank
	private String titulo;
	
	@PastOrPresent
	private LocalDate dataLancamento;
	
	@Min(10)
	private int paginas;
	
	@NotNull
	private Autor autor;
}

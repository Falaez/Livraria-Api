package br.com.alura.livraria.dto;

import java.time.LocalDate;

import br.com.alura.livraria.modelo.Autor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LivrosDto {
	
	private String titulo;
	private LocalDate dataLancamento;
	private Autor autor;
}

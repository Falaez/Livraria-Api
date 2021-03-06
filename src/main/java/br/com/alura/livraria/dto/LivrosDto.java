package br.com.alura.livraria.dto;

import java.time.LocalDate;

import br.com.alura.livraria.modelo.Autor;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LivrosDto {
	
	private Long id;
	private String titulo;
	private LocalDate dataLancamento;
	private Autor autor;
}

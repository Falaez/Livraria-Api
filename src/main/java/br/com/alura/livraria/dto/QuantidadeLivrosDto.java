package br.com.alura.livraria.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class QuantidadeLivrosDto {
	
	private String autor;
	private Long quantidadeDeLivros;
	private Double percentual;
	
	
	
}

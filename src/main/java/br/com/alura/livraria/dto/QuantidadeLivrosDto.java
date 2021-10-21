package br.com.alura.livraria.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class QuantidadeLivrosDto {
	
	private String autor;
	private Long quantidadeDeLivros;
	private Double percentual;
}

package br.com.alura.livraria.dto;

import java.time.LocalDate;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AutorDetalhadoDto extends AutorDto {
	
	private String email;
	private LocalDate dataNascimento;
	private String miniCurriculo;
}

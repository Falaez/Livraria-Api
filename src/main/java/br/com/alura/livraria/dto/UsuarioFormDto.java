package br.com.alura.livraria.dto;

import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UsuarioFormDto {
	
	@NotNull
	private String nome;
	@NotNull
	private String login;
	@NotNull
	private String senha;
	@NotNull
	private Long PerfilId;
}

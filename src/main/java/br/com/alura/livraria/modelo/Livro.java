package br.com.alura.livraria.modelo;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "livros")
public class Livro {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String titulo;
	private LocalDate dataLancamento;
	private Integer paginas;
	
	@ManyToOne
	private Autor autor;

	public Livro(String titulo, LocalDate dataLancamento, Integer paginas, Autor autor) {
		this.titulo = titulo;
		this.dataLancamento = dataLancamento;
		this.paginas = paginas;
		this.autor = autor;
	}
	
	public void atualizarInformacao(String titulo, LocalDate dataLancamento, Integer paginas, Autor autor) {
		this.titulo = titulo;
		this.dataLancamento = dataLancamento;
		this.paginas = paginas;
		this.autor = autor;
	}
	
}

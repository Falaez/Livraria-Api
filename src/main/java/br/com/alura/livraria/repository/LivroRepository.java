package br.com.alura.livraria.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.alura.livraria.dto.QuantidadeLivrosDto;
import br.com.alura.livraria.modelo.Livro;

@Repository
public interface LivroRepository extends JpaRepository<Livro, Long> {

	
	
	@Query("select new br.com.alura.livraria.dto.QuantidadeLivrosDto("
			+"l.autor.nome, count(*), round(count(*) * 1.0 / (select count(*) from Livro) * 100.0,1)    as percentual)  "
			+ "from Livro l group by l.autor order by percentual desc")
	List<QuantidadeLivrosDto> relatorioQuantidadeLivros();
	
	List<Livro> findByAutor(String autor);
}

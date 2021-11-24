package br.com.alura.livraria.repository;

import java.time.LocalDate;
import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import br.com.alura.livraria.dto.QuantidadeLivrosDto;
import br.com.alura.livraria.modelo.Autor;
import br.com.alura.livraria.modelo.Livro;



@ExtendWith(SpringExtension.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@ActiveProfiles("test")
class LivroRepositoryTest {
	
	@Autowired
	private LivroRepository repository;
	
	@Autowired
	private TestEntityManager em;
	
	@Test
	void deveriaRetornarRelatorioDeLivros() {
		Autor andre = new Autor("Andre da Silva","andre@gmail.com",LocalDate.of(2000, 01, 01),"...");
		Autor fernanda = new Autor("Fernanda Nogueira","fer@gmail.com",LocalDate.of(2000, 01, 01),"...");
		Autor juliana = new Autor("Juliana Carvalho","ju@gmail.com",LocalDate.of(2000, 01, 01),"...");
		Autor rita = new Autor("Rita de Assis","rita@gmail.com",LocalDate.of(2000, 01, 01),"...");
		Autor rodrigo = new Autor("Rodrigo de Souza","rodrigo@gmail.com",LocalDate.of(2000, 01, 01),"...");
		em.persist(andre);
		em.persist(fernanda);
		em.persist(juliana);
		em.persist(rita);
		em.persist(rodrigo);
		Livro l1 = new Livro("Aprenda Java",LocalDate.of(2021, 04, 10),10, andre);
		em.persist(l1);
		Livro l2 = new Livro("Como ser produtivo",LocalDate.of(2021, 04, 10),10, fernanda);
		em.persist(l2);
		Livro l3 = new Livro("Aprenda a falar em publico",LocalDate.of(2021, 04, 10),10, juliana);
		em.persist(l3);
		Livro l4 = new Livro("Otimizando seu tempo",LocalDate.of(2021, 04, 10),10, fernanda);
		em.persist(l4);
		Livro l5 = new Livro("Como fazer bolos",LocalDate.of(2021, 04, 10),10, rita);
		em.persist(l5);
		Livro l6 = new Livro("Investido",LocalDate.of(2021, 04, 10),10, rodrigo);
		em.persist(l6);
		Livro l7 = new Livro("Aprenda Python",LocalDate.of(2021, 04, 10),10, andre);
		em.persist(l7);
		List<QuantidadeLivrosDto> relatorio = repository.relatorioQuantidadeLivros();
		
		Assertions.assertThat(relatorio).hasSize(5).extracting(QuantidadeLivrosDto::getAutor, QuantidadeLivrosDto::getQuantidadeDeLivros,
				QuantidadeLivrosDto::getPercentual).
		containsExactlyInAnyOrder(
				Assertions.tuple("Andre da Silva", 2l,28.6),
				Assertions.tuple("Fernanda Nogueira",2L,28.6),
				Assertions.tuple("Juliana Carvalho",1L,14.3),
				Assertions.tuple("Rita de Assis",1L,14.3),
				Assertions.tuple("Rodrigo de Souza",1L,14.3));
	}
	}

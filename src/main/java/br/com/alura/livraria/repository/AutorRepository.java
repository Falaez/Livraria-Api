package br.com.alura.livraria.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.alura.livraria.modelo.Autor;

@Repository
public interface AutorRepository extends JpaRepository<Autor, Long>{

}

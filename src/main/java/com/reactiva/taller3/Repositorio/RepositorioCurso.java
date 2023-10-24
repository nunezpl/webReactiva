package com.reactiva.taller3.Repositorio;

import com.reactiva.taller3.Modelo.Curso;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepositorioCurso extends ReactiveCrudRepository<Curso, Integer> {

}

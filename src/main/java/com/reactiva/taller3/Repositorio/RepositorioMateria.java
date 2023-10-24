package com.reactiva.taller3.Repositorio;

import com.reactiva.taller3.Modelo.Materia;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepositorioMateria extends ReactiveCrudRepository<Materia, Integer> {
}

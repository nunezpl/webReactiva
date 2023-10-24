package com.reactiva.taller3.Repositorio;

import com.reactiva.taller3.Modelo.Nota;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepositorioNota extends ReactiveCrudRepository<Nota, Integer> {
}

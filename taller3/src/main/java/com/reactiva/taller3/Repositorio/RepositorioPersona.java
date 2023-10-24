package com.reactiva.taller3.Repositorio;

import com.reactiva.taller3.Modelo.Persona;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepositorioPersona extends ReactiveCrudRepository<Persona, Integer> {

}

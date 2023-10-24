package com.reactiva.taller3.Servicio;

import com.reactiva.taller3.Modelo.Persona;
import com.reactiva.taller3.Repositorio.RepositorioPersona;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class ServicioPersona {

    @Autowired
    private RepositorioPersona repositorioPersona;

    public Flux<Persona> list() {
        return repositorioPersona.findAll();
    }

    Mono<Persona> create(Mono<Persona> personaMono){
        return personaMono.flatMap(repositorioPersona::save);
    }

    Mono<Persona> retrieve(Integer id){
        return repositorioPersona.findById(id);
    }

    Mono<Void> delete(Integer id){
        return repositorioPersona.deleteById(id);
    }

}

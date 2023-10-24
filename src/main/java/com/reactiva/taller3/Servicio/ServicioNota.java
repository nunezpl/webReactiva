package com.reactiva.taller3.Servicio;

import com.reactiva.taller3.Modelo.Nota;
import com.reactiva.taller3.Repositorio.RepositorioNota;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Service
public class ServicioNota {

    @Autowired
    private RepositorioNota repositorioNota;

    public Flux<Nota> list() {
        return repositorioNota.findAll();
    }

    public Flux<Nota> notasId(Integer id){
        Flux<Nota> notas = repositorioNota.findAll()
                .filter(nota -> nota.getEstudiante().equals(id) || nota.getProfesor().equals(id))
                .doOnError(error -> {
                    System.err.println("Error: " + error.getMessage());
                });

        return notas;
    }
}

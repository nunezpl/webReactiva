package com.reactiva.taller3.Servicio;

import com.reactiva.taller3.Modelo.Nota;
import com.reactiva.taller3.Repositorio.RepositorioNota;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class ServicioNota {

    @Autowired
    private RepositorioNota repositorioNota;

    public Flux<Nota> list() {
        return repositorioNota.findAll();
    }

    public Flux<Nota> notasId(Integer id){
        Flux<Nota> notas = repositorioNota.findAll()
                .filter(nota -> nota.getEstudiante_id().equals(id) || nota.getProfesor_id().equals(id))
                .doOnError(error -> {
                    System.err.println("Error: " + error.getMessage());
                });

        return notas;
    }

    public void borraId(Integer id){
        Flux<Nota> n = repositorioNota.findAll()
                .filter(nota -> nota.getEstudiante_id().equals(id) || nota.getProfesor_id().equals(id));

        n.collectList().subscribe(notasAEliminar -> {
            repositorioNota.deleteAll(notasAEliminar);
            System.out.println("Borrando notas de " + id);
        });
    }

    public Mono<Void> deleteNoteById(Integer id) {
        return null;
    }

    public Mono<Nota> createNote(Nota note) {
        return null;
    }
}

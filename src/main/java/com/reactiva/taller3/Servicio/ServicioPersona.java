package com.reactiva.taller3.Servicio;

import com.reactiva.taller3.Modelo.Persona;
import com.reactiva.taller3.Repositorio.RepositorioPersona;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class ServicioPersona {

    @Autowired
    private RepositorioPersona repositorioPersona;

    @Autowired
    private ServicioNota servicioNota;

    public Flux<Persona> list() {
        return repositorioPersona.findAll();
    }

    public Flux<Persona> listEst() {
        Flux<Persona> estudiantes = repositorioPersona.findAll()
                .filter(est -> est.getRol().equals('E'))
                .doOnError(error -> {
                    System.err.println("Error: " + error.getMessage());
                });

        return estudiantes;
    }

    public Flux<Persona> listPro() {
        Flux<Persona> profesores = repositorioPersona.findAll()
                .filter(pro -> pro.getRol().equals('P'))
                .doOnError(error -> {
                    System.err.println("Error: " + error.getMessage());
                });

        return profesores;
    }

    public Mono<Persona> create(Persona persona) {

        System.out.println("Creando.. ");
        return repositorioPersona.save(persona);
    }

    public Mono<Persona> getPerson(Integer id){
        return repositorioPersona.findById(id);
    }

    public Mono<ResponseEntity<Persona>> updatePerson(Integer id, Persona persona) {
        return repositorioPersona.findById(id)
                .flatMap(old -> {
                    old.setNombre(persona.getNombre());
                    old.setApellido(persona.getApellido());
                    old.setCorreo(persona.getCorreo());
                    old.setRol(persona.getRol());
                    return repositorioPersona.save(old).log();
                })
                .map(updatedBook -> new ResponseEntity<>(updatedBook, HttpStatus.OK))
                .defaultIfEmpty(new ResponseEntity<>(HttpStatus.OK));
    }

    public Mono<Persona> deletePerson(Integer id){
        return repositorioPersona.findById(id)
                .flatMap(deleted -> repositorioPersona.delete(deleted)
                        .then(Mono.just(deleted)));
    }

}

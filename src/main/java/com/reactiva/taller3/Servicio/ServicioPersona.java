package com.reactiva.taller3.Servicio;

import com.reactiva.taller3.Modelo.Nota;
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

    public Mono<Persona> create(Mono<Persona> personaMono) {
        System.out.println("Creando.. ");
        return personaMono.flatMap(repositorioPersona::save);
    }

    public Mono<Persona> getPerson(Integer id){
        return repositorioPersona.findById(id);
    }

    public void delete(Integer id){

        Mono<Persona> p = repositorioPersona.findById(id);
        p.subscribe(persona -> {
            if (persona != null) {
                System.out.println("Borrando " + persona.getNombre());

                // Borra las notas de una persona
                servicioNota.borraId(id);

                // Borra la persona
                repositorioPersona.delete(persona);
            } else {
                System.out.println("No se encontró una persona con el ID proporcionado.");
            }
        });
    }

}

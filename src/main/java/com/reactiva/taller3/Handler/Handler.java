package com.reactiva.taller3.Handler;

import com.reactiva.taller3.Modelo.Nota;
import com.reactiva.taller3.Modelo.Persona;
import com.reactiva.taller3.Repositorio.RepositorioNota;
import com.reactiva.taller3.Repositorio.RepositorioPersona;
import com.reactiva.taller3.Servicio.ServicioNota;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Collections;

import static org.springframework.web.reactive.function.BodyInserters.fromValue;

@Component
public class Handler {
    private final RepositorioPersona repositorioPersona;
    private final RepositorioNota repositorioNota;
    private final ServicioNota servicioNota;

    static Mono<ServerResponse> notFound = ServerResponse.notFound().build();

    @Autowired
    public Handler(RepositorioPersona repositorioPersona, RepositorioNota repositorioNota, ServicioNota servicioNota) {
        this.repositorioPersona = repositorioPersona;
        this.repositorioNota = repositorioNota;
        this.servicioNota = servicioNota;
    }

    public Mono<ServerResponse> getAll(ServerRequest serverRequest){
        return ServerResponse.ok()
                .contentType(MediaType.TEXT_EVENT_STREAM)
                .body(repositorioPersona.findAll().log("Func: "), Persona.class);
    }

    public Mono<ServerResponse> getAllPersons(ServerRequest serverRequest) {
        Flux<Persona> personaFlux = repositorioPersona.findAll();
        return ServerResponse.ok()
                .contentType(MediaType.TEXT_HTML)
                .render("personasPage", personaFlux.collectList());
    }

    public Mono<ServerResponse> createPerson(ServerRequest serverRequest) {
        System.out.println("handler");
        Mono<Persona> personaMono = serverRequest.bodyToMono(Persona.class);
        System.out.println("creado en handler");
        return personaMono.flatMap(persona ->
                ServerResponse.status(HttpStatus.CREATED)
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(repositorioPersona.save(persona).log("Func: "), Persona.class));
    }

    public Mono<ServerResponse> getOnePerson(ServerRequest serverRequest) {
        Integer id = Integer.valueOf(serverRequest.pathVariable("id"));
        Mono<Persona> itemMono = repositorioPersona.findById(id);
        return itemMono.flatMap(item ->
                ServerResponse.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(fromValue(item))
                        .switchIfEmpty(notFound));

    }

    public Mono<ServerResponse> getOnePersonView(ServerRequest serverRequest) {
        String id = serverRequest.pathVariable("id"); // Obtén el ID de la persona

        // Carga la persona
        Mono<Persona> personaMono = repositorioPersona.findById(Integer.valueOf(id));

        // Carga las notas
        Flux<Nota> notasFlux = servicioNota.notasId(Integer.valueOf(id));

        // Añade persona y notas al modelo
        return personaMono
                .flatMap(persona -> {
                    return ServerResponse.ok()
                            .contentType(MediaType.TEXT_HTML)
                            .render("personaMonoPage",
                                    "persona", persona, // Agrega la persona al modelo
                                    "notas", notasFlux.collectList()); // Agrega la lista de notas al modelo
                })
                .switchIfEmpty(ServerResponse.notFound().build()); // Manejo de persona no encontrada
    }


    public Mono<ServerResponse> deletePerson(ServerRequest serverRequest) {
        Integer id = Integer.valueOf(serverRequest.pathVariable("id"));
        Mono<Void> deleted = repositorioPersona.deleteById(id);

        return deleted
                .then(ServerResponse.ok()
                        .contentType(MediaType.TEXT_HTML)
                        .render("deletePerson", Collections.singletonMap("id", id))
                );
    }


    public Mono<ServerResponse> updatePerson(ServerRequest serverRequest) {
        Integer id = Integer.valueOf(serverRequest.pathVariable("id"));
        Mono<Persona> updated = serverRequest.bodyToMono(Persona.class).log("mono: ")
                .flatMap(persona -> repositorioPersona.findById(id).log()
                        .flatMap(old -> {
                            old.setNombre(persona.getNombre());
                            old.setApellido(persona.getApellido());
                            old.setCorreo(persona.getCorreo());
                            old.setRol(persona.getRol());
                            return repositorioPersona.save(old).log();
                        }));

        return updated.flatMap(persona -> ServerResponse.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(fromValue(persona)))
                .switchIfEmpty(notFound);
    }


    // NOTAS

    public Mono<ServerResponse> getAllNotes(ServerRequest serverRequest){
        return ServerResponse.ok()
                .body(repositorioNota.findAll().log("Func: "), Nota.class);
    }

    public Mono<ServerResponse> getAllNotesView(ServerRequest serverRequest) {
        Flux<Nota> personaFlux = repositorioNota.findAll();
        return ServerResponse.ok()
                .contentType(MediaType.TEXT_HTML)
                .render("notasPage", personaFlux.collectList());
    }

}

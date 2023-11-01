package com.reactiva.taller3.Handler;

import com.reactiva.taller3.Controlador.NoteController;
import com.reactiva.taller3.Controlador.PersonController;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.http.MediaType.TEXT_HTML;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;
import static org.springframework.web.reactive.function.server.RequestPredicates.*;
import static org.springframework.web.reactive.function.server.ServerResponse.ok;

@Configuration
public class Router {
    @Bean
    public RouterFunction<ServerResponse> mainRouter(Handler handler) {
        return RouterFunctions.route()
                .GET("/api/personas", accept(MediaType.TEXT_HTML), handler::getAllPersons)
                .GET("/api/personas", accept(MediaType.TEXT_EVENT_STREAM), handler::getAll)

                .GET("/api/personas/mas", request -> ok().render("creaPersonaPage"))
                .POST("/api/personas/crear", accept(MediaType.APPLICATION_JSON), handler::createPerson)

                .DELETE("/api/personas/borrar/{id}", handler::deletePerson)

                .GET("/api/personas/{id}", accept(MediaType.APPLICATION_JSON), handler::getOnePersonView)
                .GET("/api/notas", accept(MediaType.TEXT_HTML), handler::getAllNotesView)
                .GET("/api/notas", accept(MediaType.APPLICATION_JSON), handler::getAllNotes)
                .build();
    }
}

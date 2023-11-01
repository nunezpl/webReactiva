package com.reactiva.taller3.Controlador;

import com.reactiva.taller3.Modelo.Persona;
import com.reactiva.taller3.Servicio.ServicioPersona;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;

@RestController @Controller
@RequestMapping("/api/personas")
public class PersonController {
    private final ServicioPersona servicioPersona;

    @Autowired
    public PersonController(ServicioPersona servicioPersona) {
        this.servicioPersona = servicioPersona;
    }


    @GetMapping(value = "/")
    public Flux<Persona> getAll(){
        return servicioPersona.list().delayElements(Duration.ofSeconds(1));
    }

    @GetMapping("/{id}")
    public Mono<Persona> findById(@PathVariable Integer id){
        return servicioPersona.getPerson(id);
    }

    @GetMapping("/mas")
    String llenarPersona(){
        return "creaPersonaPage";
    }

    @PostMapping("/crear")
    public Mono<Persona> createPerson(@RequestBody Persona persona) {
        return servicioPersona.create(persona).log();
    }

    @PutMapping("/{id}")
    public Mono<ResponseEntity<Persona>> updateBook(@PathVariable Integer id ,@RequestBody Persona book) {
        return servicioPersona.updatePerson(id, book);
    }
}
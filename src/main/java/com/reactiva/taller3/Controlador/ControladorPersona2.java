package com.reactiva.taller3.Controlador;

import com.reactiva.taller3.Modelo.Persona;
import com.reactiva.taller3.Servicio.ServicioPersona;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ConcurrentModel;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;


@RestController
@RequestMapping("/api/personas2")
public class ControladorPersona2 {

    @Autowired
    private ServicioPersona servicioPersona;


    @GetMapping
    Flux<Persona> listarPersonas() {
        return servicioPersona.list().delayElements( Duration.ofSeconds(1) );
    }

    @GetMapping("/estudiantes")
    Flux<Persona> listarEstudiantes(Model model) {
        return servicioPersona.listEst().delayElements( Duration.ofSeconds(1) );
    }

    @GetMapping("/profesores")
    Flux<Persona> listarProfesores(Model model) {
        return servicioPersona.listPro().delayElements( Duration.ofSeconds(1) );
    }

    @GetMapping("/{id}")
    Mono<Persona> traeUnaPersona(@PathVariable Integer id, Model model) {
         return servicioPersona.getPerson(id);
    }

    @GetMapping("/borrar/{id}")
    void borraPersona(@PathVariable Integer id, Model model) {
        servicioPersona.delete(id);
    }

    @PostMapping("/crear")
    Mono<Persona> crearPersona(@RequestBody Persona persona) {
        return servicioPersona.create(Mono.just(persona));
    }

}

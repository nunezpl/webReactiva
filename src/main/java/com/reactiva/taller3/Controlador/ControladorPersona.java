package com.reactiva.taller3.Controlador;

import com.reactiva.taller3.Modelo.Persona;
import com.reactiva.taller3.Servicio.ServicioPersona;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


@Controller
//@RestController
@RequestMapping("/api/personas")
public class ControladorPersona {

    @Autowired
    private ServicioPersona servicioPersona;


    @GetMapping
    String listarPersonas(Model model) {
        Flux<Persona> personas = servicioPersona.list();
        model.addAttribute("personas", personas);
        return "personasPage";
    }

    @GetMapping("/{id}")
    String traeUnaPersona(@PathVariable Integer id, Model model) {
        Mono<Persona> p = servicioPersona.getPerson(id);
        model.addAttribute("persona", p);
        return "personaMonoPage";
    }
}

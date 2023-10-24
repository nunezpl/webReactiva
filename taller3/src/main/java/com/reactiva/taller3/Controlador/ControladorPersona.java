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

    //private static final Logger log = LoggerFactory.getLogger(ControladorPersona.class);
    //private final RepositorioPersona repositorioPersona;

    @Autowired
    private ServicioPersona servicioPersona;


    /*@GetMapping("/mostrar")
    public Mono<Persona> traePersonas() {
        return Mono.just(new Persona(1, "Maria"));
    }*/

    @GetMapping
    String listarPersonas(Model model) {
        Flux<Persona> personas = servicioPersona.list();
        model.addAttribute("personas", personas);
        return "personasPage";
    }
}

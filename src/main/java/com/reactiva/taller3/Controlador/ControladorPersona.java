package com.reactiva.taller3.Controlador;

import com.reactiva.taller3.Modelo.Nota;
import com.reactiva.taller3.Modelo.Persona;
import com.reactiva.taller3.Servicio.ServicioNota;
import com.reactiva.taller3.Servicio.ServicioPersona;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ConcurrentModel;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;


@Controller
@RequestMapping("/api/personas")
public class ControladorPersona {

    @Autowired
    private ServicioPersona servicioPersona;

    @Autowired
    private ServicioNota servicioNota;

    @GetMapping
    String listarPersonas(Model model) {
        Flux<Persona> personas = servicioPersona.list();
        model.addAttribute("personas", personas);
        return "personasPage";
    }

    @GetMapping("/estudiantes")
    String listarEstudiantes(Model model) {
        Flux<Persona> personas = servicioPersona.listEst();
        model.addAttribute("personas", personas);
        return "personasPage";
    }
    @GetMapping("/profesores")
    String listarProfesores(Model model) {
        Flux<Persona> personas = servicioPersona.listPro();
        model.addAttribute("personas", personas);
        return "personasPage";
    }

    @GetMapping("/{id}")
    String traeUnaPersona(@PathVariable Integer id, Model model) {
        Mono<Persona> p = servicioPersona.getPerson(id);
        model.addAttribute("persona", p);
        Flux<Nota> n = servicioNota.notasId(id);
        model.addAttribute("notas", n);
        return "personaMonoPage";
    }

    @GetMapping("/borrar/{id}")
    String borraPersona(@PathVariable Integer id, Model model) {
        servicioPersona.delete(id);
        return "borraPage";
    }

    @GetMapping("/mas")
    String llenarPersona(){
        return "creaPersonaPage";
    }

    @PostMapping("/crear")
    String crearPersona(@RequestBody Mono<Persona> persona) {
        System.out.println("A crear ... ");
        servicioPersona.create(persona)
                .doOnSuccess(p -> {
                    System.out.println("Persona creada");
                })
                .subscribe();
        return "redirect:/api/personas";
    }


}

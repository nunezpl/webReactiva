package com.reactiva.taller3.Controlador;

import com.reactiva.taller3.Modelo.Nota;
import com.reactiva.taller3.Modelo.Persona;
import com.reactiva.taller3.Repositorio.RepositorioNota;
import com.reactiva.taller3.Servicio.ServicioNota;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@Controller
@RequestMapping("/api/notas")
public class ControladorNota {

    @Autowired
    private ServicioNota servicioNota;

    @GetMapping
    String listarNotas(Model model) {
        Flux<Nota> notas = servicioNota.list();
        model.addAttribute("notas", notas);
        return "notasPage";
    }

    @GetMapping("/{id}")
    String listarNotasId(@PathVariable Integer id, Model model){
        Flux<Nota> notas = servicioNota.notasId(id);
        model.addAttribute("notas", notas);
        return "notasPage";
    }

}

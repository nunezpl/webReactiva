package com.reactiva.taller3.Controlador;

import com.reactiva.taller3.Modelo.Materia;
import com.reactiva.taller3.Servicio.ServicioMateria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import reactor.core.publisher.Flux;

@Controller
@RequestMapping("/api/materias")
public class ControladorMateria {

    @Autowired
    private ServicioMateria servicioMateria;

    @GetMapping
    String listarNotas(Model model) {
        Flux<Materia> materias = servicioMateria.list();
        model.addAttribute("materias", materias);
        return "materiasPage";
    }

}

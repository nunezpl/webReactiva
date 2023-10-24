package com.reactiva.taller3.Controlador;

import com.reactiva.taller3.Modelo.Curso;
import com.reactiva.taller3.Servicio.ServicioCurso;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;

@Controller
@RequestMapping("/api/cursos")
public class ControladorCurso {

    @Autowired
    private ServicioCurso servicioCurso;

    @GetMapping
    String listarNotas(Model model) {
        Flux<Curso> cursos = servicioCurso.list();
        model.addAttribute("cursos", cursos);
        return "cursosPage";
    }

}

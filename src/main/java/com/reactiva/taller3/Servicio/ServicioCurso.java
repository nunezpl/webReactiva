package com.reactiva.taller3.Servicio;

import com.reactiva.taller3.Modelo.Curso;
import com.reactiva.taller3.Modelo.Persona;
import com.reactiva.taller3.Repositorio.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class ServicioCurso {

    @Autowired
    private RepositorioCurso repositorioCurso;


    public Flux<Curso> list() {
        return repositorioCurso.findAll();
    }


}

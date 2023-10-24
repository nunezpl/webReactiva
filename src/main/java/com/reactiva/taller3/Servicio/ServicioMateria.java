package com.reactiva.taller3.Servicio;

import com.reactiva.taller3.Modelo.Materia;
import com.reactiva.taller3.Repositorio.RepositorioMateria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Service
public class ServicioMateria {

    @Autowired
    private RepositorioMateria repositorioMateria;

    public Flux<Materia> list() {
        return repositorioMateria.findAll();
    }

}

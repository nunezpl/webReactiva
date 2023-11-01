package com.reactiva.taller3.Controlador;

import com.reactiva.taller3.Modelo.Nota;
import com.reactiva.taller3.Servicio.ServicioNota;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/notas2")
public class NoteController {

    private final ServicioNota noteService;

    @Autowired
    public NoteController(ServicioNota noteService) {
        this.noteService = noteService;
    }

    @GetMapping("/")
    public Flux<Nota> getAllNotes() {
        return noteService.list();
    }

    @GetMapping("/{id}")
    public Flux<Nota> getNoteById(@PathVariable Integer id) {
        return noteService.notasId(id);
    }

    @PostMapping("/crear")
    public Mono<Nota> createNote(@RequestBody Nota note) {
        return noteService.createNote(note);
    }

    @DeleteMapping("/borrar/{id}")
    public Mono<Void> deleteNote(@PathVariable Integer id) {
        return noteService.deleteNoteById(id);
    }
}

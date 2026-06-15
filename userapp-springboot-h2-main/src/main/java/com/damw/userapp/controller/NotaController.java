package com.damw.userapp.controller;

import com.damw.userapp.model.Nota;
import com.damw.userapp.service.NotaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/notas")
public class NotaController {

    private final NotaService notaService;

    public NotaController(NotaService notaService) {
        this.notaService = notaService;
    }

    @GetMapping
    public ResponseEntity<List<Nota>> todasLasNotas() {
        List<Nota> notas = notaService.listarNotas();
        return ResponseEntity.ok(notas);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Nota> todasLasNotasPorId(@PathVariable Long id) {
        return notaService.listarNotasPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Nota> guardarNota(@RequestBody Nota nota) {
        Nota guardada = notaService.guardar(nota);
        return ResponseEntity.status(201).body(guardada);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Nota> actualizarNota(@PathVariable Long id, @RequestBody Nota nota) {
        return notaService.actualizarNotas(id, nota)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarNota(@PathVariable Long id) {
        if (notaService.borrarNotas(id)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
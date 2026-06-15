package com.damw.userapp.service;

import com.damw.userapp.model.Nota;
import com.damw.userapp.repository.NotaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NotaService {

    private final NotaRepository notaRepository;

    public NotaService(NotaRepository notaRepository) {
        this.notaRepository = notaRepository;
    }

    public List<Nota> listarNotas() {
        return notaRepository.findAll();
    }

    public Optional<Nota> listarNotasPorId(Long id) {
        return notaRepository.findById(id);
    }

    public Nota guardar(Nota nota) {
        return notaRepository.save(nota);
    }

    public Optional<Nota> actualizarNotas(Long id, Nota datosActualizados) {
        return notaRepository.findById(id).map(notaExistente -> {
            notaExistente.setTitulo(datosActualizados.getTitulo());
            notaExistente.setContenido(datosActualizados.getContenido());
            notaExistente.setAutor(datosActualizados.getAutor());
            return notaRepository.save(notaExistente);
        });
    }

    public boolean borrarNotas(Long id) {
        if (notaRepository.existsById(id)) {
            notaRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
package com.example.Practica.controller;

import com.example.Practica.dto.EstudianteDTO;
import com.example.Practica.model.Estudiante;
import com.example.Practica.service.EstudianteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/estudiantes")
public class EstudianteController {

    @Autowired
    private EstudianteService estudianteService;

    @PostMapping
    public ResponseEntity<Estudiante> crearEstudiante(@RequestBody EstudianteDTO estudianteDTO) {
        Estudiante estudiante = estudianteService.crearEstudiante(estudianteDTO);
        return new ResponseEntity<>(estudiante, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Estudiante> obtenerEstudiante(@PathVariable Long id) {
        return estudianteService.obtenerEstudiantePorId(id)
                .map(estudiante -> new ResponseEntity<>(estudiante, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Estudiante> actualizarEstudiante(@PathVariable Long id, @RequestBody EstudianteDTO estudianteDTO) {
        Estudiante estudiante = estudianteService.actualizarEstudiante(id, estudianteDTO);
        return estudiante != null
                ? new ResponseEntity<>(estudiante, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarEstudiante(@PathVariable Long id) {
        estudianteService.eliminarEstudiante(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

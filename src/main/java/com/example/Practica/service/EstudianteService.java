package com.example.Practica.service;

import com.example.Practica.dto.EstudianteDTO;
import com.example.Practica.model.Estudiante;
import com.example.Practica.repository.EstudianteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service

public class EstudianteService {
    
    @Autowired
    private EstudianteRepository estudianteRepository;

    public Estudiante crearEstudiante(EstudianteDTO estudianteDTO) {
        Estudiante estudiante = new Estudiante();
        estudiante.setNombre(estudianteDTO.getNombre());
        estudiante.setEmail(estudianteDTO.getEmail());
        estudiante.setEdad(estudianteDTO.getEdad());
        return estudianteRepository.save(estudiante);
    }

    public Optional<Estudiante> obtenerEstudiantePorId(Long id) {
        return estudianteRepository.findById(id);
    }

    public Estudiante actualizarEstudiante(Long id, EstudianteDTO estudianteDTO) {
        Estudiante estudiante = estudianteRepository.findById(id).orElse(null);
        if (estudiante != null) {
            estudiante.setNombre(estudianteDTO.getNombre());
            estudiante.setEmail(estudianteDTO.getEmail());
            estudiante.setEdad(estudianteDTO.getEdad());
            return estudianteRepository.save(estudiante);
        }
        return null;
    }

    public void eliminarEstudiante(Long id) {
        estudianteRepository.deleteById(id);
    }
}

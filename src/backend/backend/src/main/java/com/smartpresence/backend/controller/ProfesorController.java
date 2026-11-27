package com.smartpresence.backend.controller;

import com.smartpresence.backend.model.Profesor;
import com.smartpresence.backend.repository.ProfesorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/profesores")
@CrossOrigin(origins = "*") // Evita bloqueos de CORS cuando Mark conecte Flutter
@RequiredArgsConstructor
public class ProfesorController {

    private final ProfesorRepository profesorRepository;

    @GetMapping
    public List<Profesor> obtenerTodos() {
        return profesorRepository.findAll();
    }
}
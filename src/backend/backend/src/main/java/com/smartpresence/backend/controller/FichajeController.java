package com.smartpresence.backend.controller;

import com.smartpresence.backend.model.Profesor;
import com.smartpresence.backend.model.RegistroFichaje;
import com.smartpresence.backend.model.TipoFichaje;
import com.smartpresence.backend.repository.ProfesorRepository;
import com.smartpresence.backend.repository.RegistroFichajeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class FichajeController {

    private final ProfesorRepository profesorRepository;
    private final RegistroFichajeRepository registroFichajeRepository;

    // DTO simple para recibir el JSON: { "uidTarjeta": "A1B2C3D4" }
    public record FichajeRequest(String uidTarjeta) {}

    @PostMapping("/fichaje")
    public ResponseEntity<?> registrarFichaje(@RequestBody FichajeRequest request) {
        // 1. Buscar al profesor por el UID de su tarjeta RFID
        Optional<Profesor> profesorOpt = profesorRepository.findByUidTarjeta(request.uidTarjeta());

        if (profesorOpt.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                                 .body(Map.of("error", "Tarjeta no registrada: " + request.uidTarjeta()));
        }

        Profesor profesor = profesorOpt.get();

        // 2. Alternar el estado: si estaba fuera -> ENTRADA; si estaba dentro -> SALIDA
        boolean nuevoEstado = !profesor.isEnElCentro();
        TipoFichaje tipo = nuevoEstado ? TipoFichaje.ENTRADA : TipoFichaje.SALIDA;

        profesor.setEnElCentro(nuevoEstado);
        profesorRepository.save(profesor);

        // 3. Guardar el registro histórico del fichaje
        RegistroFichaje registro = new RegistroFichaje();
        registro.setProfesor(profesor);
        registro.setFechaHora(LocalDateTime.now());
        registro.setTipo(tipo);
        registroFichajeRepository.save(registro);

        // 4. Responder al ESP32 / cliente
        return ResponseEntity.ok(Map.of(
                "mensaje", "Fichaje registrado correctamente",
                "profesor", profesor.getNombre() + " " + profesor.getApellidos(),
                "tipo", tipo,
                "enElCentro", nuevoEstado,
                "fechaHora", registro.getFechaHora()
        ));
    }
}
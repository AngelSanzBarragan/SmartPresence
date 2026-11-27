package com.smartpresence.backend.repository;

import com.smartpresence.backend.model.Profesor;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface ProfesorRepository extends JpaRepository<Profesor, Long> {
    // spring genera automaticamente: SELECT * FROM profesores WHERE uid_tarjeta = ?
    Optional<Profesor> findByUidTarjeta(String uidTarjeta);
}
package com.smartpresence.backend.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "profesores")//es una tabla en PostrgreSQL
@Data
@NoArgsConstructor
@AllArgsConstructor
//de Lombok, generan todos los getters, setters y constructores
public class Profesor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "uid_tarjeta", nullable = false, unique = true)//id de la tarjeta RFID
    private String uidTarjeta;

    @Column(nullable = false)
    private String nombre;

    @Column(nullable = false)
    private String apellidos;

    @Column(nullable = false, unique = true)
    private String email;

    private String departamento;

    // indica si el profesor está actualmente dentro del centro
    @Column(name = "en_el_centro", nullable = false)
    private boolean enElCentro = false;
}
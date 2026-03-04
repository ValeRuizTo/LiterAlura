package com.literalura.model;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "autores")
public class Autor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    private Integer anioNacimiento;
    private Integer anioFallecimiento;

    @OneToMany(mappedBy = "autor")
    private List<Libro> libros;

    public Autor() {}

    public Autor(String nombre,
                 Integer anioNacimiento,
                 Integer anioFallecimiento) {
        this.nombre = nombre;
        this.anioNacimiento = anioNacimiento;
        this.anioFallecimiento = anioFallecimiento;
    }

    public String getNombre() {
        return nombre;
    }

    public Integer getAnioNacimiento() {
        return anioNacimiento;
    }

    public Integer getAnioFallecimiento() {
        return anioFallecimiento;
    }
    @Override
    public String toString() {
        return """
            -------------------------
            Autor: %s
            Nacimiento: %s
            Fallecimiento: %s
            -------------------------
            """.formatted(nombre, anioNacimiento, anioFallecimiento);
    }
}
package com.literalura.model;

import jakarta.persistence.*;

@Entity
@Table(name = "libros")
public class Libro {

    @Id
    private Long idGutendex; // ID de la API

    private String titulo;

    @ManyToOne
    @JoinColumn(name = "autor_id")
    private Autor autor;

    private String idioma;
    private Integer numeroDescargas;

    public Libro() {}

    public Libro(Long idGutendex, String titulo, Autor autor,
                 String idioma, Integer numeroDescargas) {
        this.idGutendex = idGutendex;
        this.titulo = titulo;
        this.autor = autor;
        this.idioma = idioma;
        this.numeroDescargas = numeroDescargas;
    }

    public Autor getAutor() {
        return autor;
    }

    @Override
    public String toString() {
        return """
                ------------------------------
                Título: %s
                Autor: %s
                Idioma: %s
                Descargas: %d
                ------------------------------
                """.formatted(titulo, autor.getNombre(), idioma, numeroDescargas);
    }
}
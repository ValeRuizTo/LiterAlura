package com.literalura.domain;


import java.util.List;

public class Libro {

    private Long idGutendex;
    private String titulo;
    private List<Autor> autores;
    private List<String> idiomas;
    private Integer numeroDescargas;

    public Libro() {}

    public Libro(Long idGutendex, String titulo, List<Autor> autores,
                 List<String> idiomas, Integer numeroDescargas) {
        this.idGutendex = idGutendex;
        this.titulo = titulo;
        this.autores = autores;
        this.idiomas = idiomas;
        this.numeroDescargas = numeroDescargas;
    }

    public Long getIdGutendex() {
        return idGutendex;
    }

    public void setIdGutendex(Long idGutendex) {
        this.idGutendex = idGutendex;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public List<Autor> getAutores() {
        return autores;
    }

    public void setAutores(List<Autor> autores) {
        this.autores = autores;
    }

    public List<String> getIdiomas() {
        return idiomas;
    }

    public void setIdiomas(List<String> idiomas) {
        this.idiomas = idiomas;
    }

    public Integer getNumeroDescargas() {
        return numeroDescargas;
    }

    public void setNumeroDescargas(Integer numeroDescargas) {
        this.numeroDescargas = numeroDescargas;
    }

    @Override
    public String toString() {
        return "Libro{" +
                "idGutendex=" + idGutendex +
                ", titulo='" + titulo + '\'' +
                ", autores=" + autores +
                ", idiomas=" + idiomas +
                ", numeroDescargas=" + numeroDescargas +
                '}';
    }
}
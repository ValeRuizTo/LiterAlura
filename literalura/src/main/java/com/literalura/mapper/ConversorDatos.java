package com.literalura.mapper;


import com.literalura.domain.Autor;
import com.literalura.domain.Libro;
import com.literalura.model.DatosAutor;
import com.literalura.model.DatosLibro;

import java.util.List;
import java.util.stream.Collectors;

public class ConversorDatos {

    public Libro convertirLibro(DatosLibro datosLibro) {

        List<Autor> autores = datosLibro.autores().stream()
                .map(this::convertirAutor)
                .collect(Collectors.toList());

        return new Libro(
                datosLibro.id(),
                datosLibro.titulo(),
                autores,
                datosLibro.idiomas(),
                datosLibro.numeroDescargas()
        );
    }

    public Autor convertirAutor(DatosAutor datosAutor) {
        return new Autor(
                datosAutor.nombre(),
                datosAutor.anioNacimiento(),
                datosAutor.anioFallecimiento()
        );
    }
}
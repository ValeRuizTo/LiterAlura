package com.literalura.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DatosLibro(

        Long id,

        @JsonAlias("title")
        String titulo,

        List<DatosAutor> authors,

        List<String> languages,

        @JsonAlias("download_count")
        Integer numeroDescargas

) {}
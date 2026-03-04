package com.literalura.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DatosAutor(

        String name,

        @JsonAlias("birth_year")
        Integer anioNacimiento,

        @JsonAlias("death_year")
        Integer anioFallecimiento

) {}
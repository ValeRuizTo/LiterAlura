package com.literalura.repository;

import com.literalura.model.Autor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AutorRepository extends JpaRepository<Autor, Long> {

    @Query("""
           SELECT a 
           FROM Autor a 
           WHERE a.anioNacimiento <= :anio 
             AND (a.anioFallecimiento IS NULL OR a.anioFallecimiento >= :anio)
           """)
    List<Autor> findAutoresVivosEnAnio(@Param("anio") Integer anio);

}
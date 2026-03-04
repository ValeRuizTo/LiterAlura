package com.literalura.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.literalura.dto.DatosAutor;
import com.literalura.dto.DatosLibro;
import com.literalura.dto.DatosRespuesta;
import com.literalura.model.Autor;
import com.literalura.model.Libro;
import com.literalura.repository.AutorRepository;
import com.literalura.repository.LibroRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.List;

@Service
public class LibroService {

    private final String URL_BASE = "https://gutendex.com/books/?search=";

    private final LibroRepository repository;
    private final AutorRepository autorRepository;

    // ✅ Constructor correcto con ambos repositories
    public LibroService(LibroRepository repository,
                        AutorRepository autorRepository) {
        this.repository = repository;
        this.autorRepository = autorRepository;
    }

    public void buscarLibroPorTitulo(String titulo) {

        try {
            RestTemplate restTemplate = new RestTemplate();

            // ✅ Codificar título correctamente
            String tituloCodificado = URLEncoder.encode(titulo, StandardCharsets.UTF_8);

            String json = restTemplate.getForObject(
                    URL_BASE + tituloCodificado,
                    String.class
            );

            ObjectMapper mapper = new ObjectMapper();
            DatosRespuesta respuesta = mapper.readValue(json, DatosRespuesta.class);

            if (respuesta.results().isEmpty()) {
                System.out.println("❌ No se encontró el libro.");
                return;
            }

            DatosLibro datos = respuesta.results().get(0);

            // =========================
            // CREAR AUTOR (seguro)
            // =========================
            Autor autor;

            if (datos.authors().isEmpty()) {
                autor = new Autor("Desconocido", null, null);
            } else {
                DatosAutor datosAutor = datos.authors().get(0);

                autor = new Autor(
                        datosAutor.name(),
                        datosAutor.anioNacimiento(),
                        datosAutor.anioFallecimiento()
                );
            }

            autorRepository.save(autor);

            // =========================
            // IDIOMA (solo el primero)
            // =========================
            String idioma = datos.languages().isEmpty()
                    ? "Desconocido"
                    : datos.languages().get(0);

            // =========================
            // CREAR LIBRO
            // =========================
            Libro libro = new Libro(
                    datos.id(),
                    datos.titulo(),
                    autor,
                    idioma,
                    datos.numeroDescargas()
            );

            repository.save(libro);

            System.out.println("✅ Libro guardado correctamente:");
            System.out.println(libro);

        } catch (Exception e) {
            System.out.println("⚠️ Error al buscar libro.");
            e.printStackTrace();
        }
    }

    // =========================
    // LISTAR LIBROS
    // =========================
    public void listarLibros() {
        List<Libro> libros = repository.findAll();

        if (libros.isEmpty()) {
            System.out.println("⚠️ No hay libros guardados.");
            return;
        }

        libros.forEach(System.out::println);
    }

    // =========================
    // LISTAR POR IDIOMA
    // =========================
    public void listarPorIdioma(String idioma) {
        List<Libro> libros = repository.findByIdioma(idioma);

        if (libros.isEmpty()) {
            System.out.println("⚠️ No hay libros en ese idioma.");
            return;
        }

        libros.forEach(System.out::println);
    }

    // =========================
    // LISTAR AUTORES
    // =========================
    public void listarAutores() {
        List<Autor> autores = autorRepository.findAll();

        if (autores.isEmpty()) {
            System.out.println("⚠️ No hay autores guardados.");
            return;
        }

        autores.forEach(System.out::println);
    }

    // =========================
    // AUTORES VIVOS EN AÑO
    // =========================
    public void listarAutoresVivosEnAnio(Integer anio) {
        if (anio == null || anio <= 0) {
            System.out.println("⚠️ Año inválido.");
            return;
        }

        List<Autor> autores = autorRepository.findAutoresVivosEnAnio(anio);

        if (autores.isEmpty()) {
            System.out.println("⚠️ No se encontraron autores vivos en ese año.");
            return;
        }

        System.out.println("\n📅 Autores vivos en el año " + anio + ":");
        autores.forEach(System.out::println);
    }

    public void mostrarEstadisticasPorIdioma() {

        Long cantidadEspanol = repository.countByIdioma("es");
        Long cantidadIngles = repository.countByIdioma("en");

        System.out.println("""
            
            📊 Estadísticas por idioma
            ---------------------------------
            Cantidad de libros en español: %d
            Cantidad de libros en inglés: %d
            ---------------------------------
            """.formatted(cantidadEspanol, cantidadIngles));
    }
}
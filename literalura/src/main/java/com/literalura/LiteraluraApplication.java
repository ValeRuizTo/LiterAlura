package com.literalura;

import com.literalura.Service.LibroService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import java.util.Scanner;

@SpringBootApplication
public class LiteraluraApplication implements CommandLineRunner {

	private final LibroService libroService;

	public LiteraluraApplication(LibroService libroService) {
		this.libroService = libroService;
	}

	public static void main(String[] args) {
		SpringApplication.run(LiteraluraApplication.class, args);
	}

	@Override
	public void run(String... args) {

		Scanner scanner = new Scanner(System.in);
		int opcion = -1;

		while (opcion != 0) {

			System.out.println("""
                    =====================================
                    📚 LITERALURA
                    =====================================
					1 - Buscar libro por título
					         2 - Listar todos los libros
					         3 - Listar libros por idioma
					         4 - Listar autores
					         5 - Listar autores vivos en determinado año
					         6 - Mostrar estadísticas por idioma
					         0 - Salir
                    """);

			System.out.print("Seleccione una opción: ");
			opcion = scanner.nextInt();
			scanner.nextLine();

			switch (opcion) {

				case 1:
					System.out.print("Ingrese el título: ");
					String titulo = scanner.nextLine();
					libroService.buscarLibroPorTitulo(titulo);
					break;

				case 2:
					libroService.listarLibros();
					break;

				case 3:
					System.out.print("Ingrese idioma (es, en, fr): ");
					String idioma = scanner.nextLine();
					libroService.listarPorIdioma(idioma);
					break;

				case 4:
					libroService.listarAutores();
					break;

				case 5:
					System.out.print("Ingrese el año: ");

					try {
						int anio = Integer.parseInt(scanner.nextLine());

						if (anio <= 0) {
							System.out.println("⚠️ Debe ingresar un año válido mayor a 0.");
						} else {
							libroService.listarAutoresVivosEnAnio(anio);
						}

					} catch (NumberFormatException e) {
						System.out.println("⚠️ Entrada inválida. Debe ingresar un número.");
					}

					break;
				case 6:
					libroService.mostrarEstadisticasPorIdioma();
					break;

				case 0:
					System.out.println("Saliendo...");
					break;

				default:
					System.out.println("Opción inválida.");
			}
		}
	}
}
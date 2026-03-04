package com.literalura.Service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ConsumoAPI {
    public String obtenerDatos(String direccion) {

        // 1️⃣ Crear cliente HTTP
        HttpClient client = HttpClient.newHttpClient();

        // 2️⃣ Construir la solicitud
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(direccion))
                .GET()
                .build();

        try {
            // 3️⃣ Enviar solicitud y recibir respuesta
            HttpResponse<String> response =
                    client.send(request, HttpResponse.BodyHandlers.ofString());

            // 4️⃣ Retornar el cuerpo de la respuesta (JSON)
            return response.body();

        } catch (IOException | InterruptedException e) {
            throw new RuntimeException("Error al consumir la API", e);
        }
    }
}

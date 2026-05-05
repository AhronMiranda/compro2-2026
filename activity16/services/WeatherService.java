package com.weather.services;

import com.google.gson.Gson;
import com.weather.models.WeatherResponse;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class WeatherService {

    private final HttpClient client;
    private final Gson gson;

    public WeatherService() {
        client = HttpClient.newHttpClient();
        gson = new Gson();
    }

    public WeatherResponse getForecast(double lat, double lon) {

        String url = "https://www.7timer.info/bin/astro.php"
                + "?lon=" + lon
                + "&lat=" + lat
                + "&ac=0"
                + "&unit=metric"
                + "&output=json";

        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .GET()
                    .build();

            HttpResponse<String> response = client.send(
                    request,
                    HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() == 200) {
                WeatherResponse weather = gson.fromJson(
                        response.body(),
                        WeatherResponse.class);

                if (weather != null
                        && weather.getForecasts() != null
                        && !weather.getForecasts().isEmpty()) {
                    return weather;
                }
            }

        } catch (IOException | InterruptedException e) {
            System.out.println("Error retrieving weather data: " + e.getMessage());
        }

        return null;
    }
}
package com.weather;

import com.weather.models.Forecast;
import com.weather.models.WeatherResponse;
import com.weather.services.WeatherService;

import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        WeatherService weatherService = new WeatherService();

        try {
            System.out.print("Enter Latitude: ");
            double lat = sc.nextDouble();

            System.out.print("Enter Longitude: ");
            double lon = sc.nextDouble();

            WeatherResponse response = weatherService.getForecast(lat, lon);

            if (response == null) {
                System.out.println("Could not retrieve weather data.");
                return;
            }

            List<Forecast> forecasts = response.getForecasts();

            System.out.println("\nWeather Forecast:\n");

            for (int i = 0; i < 3 && i < forecasts.size(); i++) {
                Forecast forecast = forecasts.get(i);

                System.out.println(
                        "At hour " + forecast.getTimepoint()
                                + ": " + forecast.getTemperature() + "°C"
                                + " with " + forecast.getWind().getSpeed()
                                + " speed winds from the "
                                + forecast.getWind().getDirection() + "."
                );
            }

        } catch (Exception e) {
            System.out.println("Invalid input.");
        } finally {
            sc.close();
        }
    }
}
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.io.IOException;
import java.util.Scanner;

public class WeatherFetcher2 {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        try {
            // User input
            System.out.print("Enter Latitude: ");
            String lat = scanner.nextLine();

            System.out.print("Enter Longitude: ");
            String lon = scanner.nextLine();

            // Build API URL dynamically
            String url = "https://www.7timer.info/bin/astro.php"
                    + "?lon=" + lon
                    + "&lat=" + lat
                    + "&ac=0"
                    + "&unit=metric"
                    + "&output=json";

            // Create HttpClient
            HttpClient client = HttpClient.newHttpClient();

            // Build HttpRequest
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .GET()
                    .build();

            // Send request synchronously
            HttpResponse<String> response = client.send(
                    request,
                    HttpResponse.BodyHandlers.ofString()
            );

            // Check status code
            if (response.statusCode() == 200) {
                System.out.println("\nWeather Data Retrieved Successfully:");
                System.out.println(response.body());
            } else {
                System.out.println("Error: Failed to fetch weather data.");
                System.out.println("HTTP Status Code: " + response.statusCode());
            }

        } catch (IOException e) {
            System.out.println("Network Error: Unable to connect to the weather service.");
            System.out.println(e.getMessage());

        } catch (InterruptedException e) {
            System.out.println("Request was interrupted.");
            System.out.println(e.getMessage());
        }

        scanner.close();
    }
}
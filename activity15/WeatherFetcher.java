import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.io.IOException;
import java.util.Scanner;

public class WeatherFetcher {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        try {
            System.out.print("Enter Latitude: ");
            String lat = sc.nextLine();

            System.out.print("Enter Longitude: ");
            String lon = sc.nextLine();

            
            String url = "https://www.7timer.info/bin/astro.php"
                    + "?lon=" + lon
                    + "&lat=" + lat
                    + "&ac=0"
                    + "&unit=metric"
                    + "&output=json";

            
            HttpClient client = HttpClient.newHttpClient();

            
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .GET()
                    .build();

            
            HttpResponse<String> response = client.send(
                    request,
                    HttpResponse.BodyHandlers.ofString()
            );

            
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

        sc.close();
    }
}
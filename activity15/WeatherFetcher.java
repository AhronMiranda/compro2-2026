import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import java.util.Scanner;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

public class WeatherFetcher {

    public static Scanner sc = new Scanner(System.in);
    private static final String FILE = "weather.json";
    private static final Gson gson = new GsonBuilder()
            .setPrettyPrinting()
            .create();

    public static void main(String[] args) {

        HttpClient client = HttpClient.newHttpClient();

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://www.7timer.info/bin/astro.php"))
                .header("Accept", "application/json")
                .GET()
                .build();

        try {

            HttpResponse<String> response = client.send(
                    request,
                    HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() == 200) {
                System.out.println("Success! Data: " + response.body());
            } else {
                System.out.println("Error Code: " + response.statusCode());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
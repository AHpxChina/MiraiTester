package Utils.Http;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Map;

public class HttpUtilities {
    public static String get(String url) {
        var client = HttpClient.newBuilder()
                .followRedirects(HttpClient.Redirect.ALWAYS)
                .build();

        var request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .GET()
                .build();

        try {
            var response = client.send(request, HttpResponse.BodyHandlers.ofString());

            return response.body();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public static String postJson(String url, String json){
        var client = HttpClient.newBuilder()
                .followRedirects(HttpClient.Redirect.ALWAYS)
                .build();

        var request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .POST(HttpRequest.BodyPublishers.ofString(json))
                .header("Content-Type", "application/json;UTF-8")
                .build();

        try {
            var response = client.send(request, HttpResponse.BodyHandlers.ofString());

            return response.body();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public static String get(String url, @NotNull Map<String, String> headers) {
        var client = HttpClient.newBuilder()
                .followRedirects(HttpClient.Redirect.ALWAYS)
                .build();

        var requestBuilder = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .GET();

        return sendWithHeaders(headers, client, requestBuilder);
    }

    @Nullable
    private static String sendWithHeaders(@NotNull Map<String, String> headers, HttpClient client, HttpRequest.Builder requestBuilder) {
        headers.forEach(requestBuilder::header);

        var request = requestBuilder.build();

        try {
            var response = client.send(request, HttpResponse.BodyHandlers.ofString());

            return response.body();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public static String postJson(String url, String json, @NotNull Map<String, String> headers) {
        var client = HttpClient.newBuilder()
                .followRedirects(HttpClient.Redirect.ALWAYS)
                .build();

        var requestBuilder = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .POST(HttpRequest.BodyPublishers.ofString(json))
                .header("Content-Type", "application/json;UTF-8");

        return sendWithHeaders(headers, client, requestBuilder);
    }
}

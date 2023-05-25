package navy_battle;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class MyApiTest {

    private final HttpClient client = HttpClient.newHttpClient();

    @Test
    public void handlePingTest() throws Exception {
        int port = 9876;
        String[] args = new String[]{String.valueOf(port)};
        Thread serverThread = new Thread(() -> {
            try {
                Launcher.main(args);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        serverThread.start();

        // Attendez un court instant pour permettre au serveur de démarrer
        Thread.sleep(1000);

        HttpRequest request = HttpRequest.newBuilder().uri(URI.create("http://localhost:" + port + "/ping")).GET().build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        Assertions.assertEquals("OK", response.body());

        // Arrêtez le serveur après les tests
        serverThread.interrupt();
    }
}

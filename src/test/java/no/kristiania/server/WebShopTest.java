package no.kristiania.server;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

import static org.assertj.core.api.Assertions.assertThat;

class WebShopTest {

    private WebShop server;

    @BeforeEach
    public void setupServer() throws Exception {
        this.server = new WebShop(0);
        server.start();
    }

    @Test
    public void getStatusCode200Test() throws Exception {
        var connection = openConnection("/");
        assertThat(connection.getResponseCode())
                .as("check for 200")
                .isEqualTo(200);

    }

    @Test
    public void getRequestAllProductsTest() throws Exception {
        var connection = openConnection("/api/products");
        assertThat(connection.getInputStream())
                .asString(StandardCharsets.UTF_8)
                .contains("[{\"name\":\"Laptop\",\"category\":\"Computers\"");
    }



    private HttpURLConnection openConnection(String spec) throws IOException {
        return (HttpURLConnection) new URL(server.getURL(), spec).openConnection();

    }

}
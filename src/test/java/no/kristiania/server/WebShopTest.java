package no.kristiania.server;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class WebShopTest {

    private WebShop server;

    @Test
    public void getStatusCode200Test() throws Exception {

        this.server = new WebShop(0);
        server.start();
        var connection = openConnection("/");
        assertThat(connection.getResponseCode()).as("check for 200").isEqualTo(200);

    }
    @Test
    public void getRequestAllProductsTest() throws Exception {

        this.server = new WebShop(0);
        server.start();
        var connection = openConnection("/api/products");

        assertThat(connection.getInputStream()).asString(StandardCharsets.UTF_8).contains("hei");

    }



    private HttpURLConnection openConnection(String spec) throws IOException {
        return (HttpURLConnection) new URL(server.getURL(), spec).openConnection();
    }

}
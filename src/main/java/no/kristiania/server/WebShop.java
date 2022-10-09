package no.kristiania.server;

import org.eclipse.jetty.server.Server;

import java.net.MalformedURLException;
import java.net.URL;

public class WebShop {
    private final Server server;

    public WebShop(int port) {
        this.server = new Server(port);

    }
    public void start() throws Exception {

        server.start();
    }
    public static void main(String[] args) throws Exception {
        System.out.println("Hello World");
        var serv = new WebShop(8080);
        serv.start();
        System.out.println("server started");


    }

    public URL getURL() throws MalformedURLException {
        return server.getURI().toURL();

    }
}

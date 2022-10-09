package no.kristiania.server;


import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.util.resource.Resource;
import org.eclipse.jetty.webapp.WebAppContext;

import org.glassfish.jersey.servlet.ServletContainer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.MalformedURLException;
import java.net.URL;

public class WebShop {

    private final Server server;
    private final static Logger logger = LoggerFactory.getLogger(WebShop.class);

    public WebShop(int port) {
        this.server = new Server(port);
        var resources = Resource.newClassPathResource("/");

        var webAppContext = new WebAppContext(resources, "/");
        var servletHolder =webAppContext.addServlet(ServletContainer.class,"/api/*");
        servletHolder.setInitParameter("jersey.config.server.provider.packages","no.kristiania.sever");

        server.setHandler(webAppContext);
    }


    public void start() throws Exception {
        server.start();
        logger.warn("Server is starting on {}", getURL());
    }

    public URL getURL() throws MalformedURLException {
        return server.getURI().toURL();
    }

    public static void main(String[] args) throws Exception {
        var serv = new WebShop(8080);
        serv.start();

    }


}

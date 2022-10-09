package no.kristiania.server;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.Response;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

@Path("/products")
public class ProductEndpoint{
    private final static List<Product> products = new ArrayList<>();
    @GET
    public Response getAllItems(){

        return Response.ok("hei").build();
    }

}

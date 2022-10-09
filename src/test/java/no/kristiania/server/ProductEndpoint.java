package no.kristiania.server;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.Response;

import java.util.ArrayList;
import java.util.List;

@Path("/item")
public class ProductEndpoint{
    private final static List<Product> products = new ArrayList<>();
    @GET
    public Response getAllItems(){
        return Response.ok().build();
    }
}

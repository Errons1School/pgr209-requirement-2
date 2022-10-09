package no.kristiania.server;

import jakarta.json.Json;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.Response;

import java.util.ArrayList;
import java.util.List;

@Path("/products")
public class ProductEndpoint{

    private final static List<Product> products = new ArrayList<>();

    static {
        products.add(new Product(
                "Laptop",
                "Computers",
                "",
                "This is a good laptop",
                14955,
                15
        ));
    }

    @GET
    public Response getAllItems(){
        var result = Json.createArrayBuilder();
        for (var prod : products) {
            result.add(Json.createObjectBuilder()
                    .add("name", prod.getName())
                    .add("category", prod.getCategory())
                    .add("img", prod.getImg())
                    .add("description", prod.getDescription())
                    .add("price", prod.getPrice())
                    .add("stock", prod.getStock())
            );
        }
        return Response.ok(result.build().toString()).build();
    }

}

package no.kristiania.server;

import jakarta.json.Json;
import jakarta.json.JsonObject;
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
    public Response getAllProducts(){
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
    @POST
    public Response addProducts(String body){
        var stringReader = new StringReader(body);
        JsonObject jsonBody = Json.createReader(new StringReader(body)).readObject();

        products.add(new Product(jsonBody.getString("name"),
                jsonBody.getString("category"),
                jsonBody.getString("img"),
                jsonBody.getString("description"),
                jsonBody.getInt("price"),
                jsonBody.getInt("stock")));

       return Response.ok().build();

    }

}

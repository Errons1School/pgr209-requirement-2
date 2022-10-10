package no.kristiania.server;

import jakarta.json.Json;
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
                "acer",
                "laptop",
                "",
                "This is a good laptop",
                14955,
                15
        ));
        products.add(new Product(
                "mac air",
                "laptop",
                "",
                "best there is",
                22955,
                2
        ));
        products.add(new Product(
                "legion",
                "laptop",
                "",
                "best there is",
                22955,
                2
        ));
        products.add(new Product(
                "racer naga",
                "mouse",
                "",
                "best there is",
                255,
                10
        ));
        products.add(new Product(
                "dell E200",
                "mouse",
                "",
                "best there is",
                255,
                10
        ));
        products.add(new Product(
                "RTX3090",
                "gpu",
                "",
                "best there is",
                15000,
                100
        ));
        products.add(new Product(
                "RTX4090",
                "gpu",
                "",
                "best there is",
                25500,
                1
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

        System.out.println("Successful get request!");
        return Response.ok(result.build().toString()).build();
    }

    @POST
    public Response addProducts(String body){
        var reader = new StringReader(body);
        var jsonBody = Json.createReader(reader).readObject();

        var tmpProd = new Product(
                jsonBody.getString("name"),
                jsonBody.getString("category"),
                jsonBody.getString("img"),
                jsonBody.getString("description"),
                jsonBody.getInt("price"),
                jsonBody.getInt("stock")
        );

        products.add(tmpProd);
        System.out.println("Added Product! Name:'" + tmpProd.getName() + "'");
        return Response.ok().build();

    }

}

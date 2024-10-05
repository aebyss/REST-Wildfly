package de.pdbm;

import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;

@Path("/hello")
public class HelloWorld {



    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public JsonObject getJsonResponse() {
        JsonObject json = Json.createObjectBuilder()
                .add("message", "Hello")
                .add("status", "success")
                .build();

        return json;
    }
}

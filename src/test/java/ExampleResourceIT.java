import jakarta.json.bind.Jsonb;
import jakarta.json.bind.JsonbBuilder;
import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.WebTarget;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class ExampleResourceIT {
    private static Client client;
    private static WebTarget target;

    @BeforeAll
    public static void setUp() {
        client = ClientBuilder.newClient();
        target = client.target("http://localhost:8080/starter/api/hello");
    }

    @AfterAll
    public static void tearDown() {
        if (client != null) {
            client.close();
        }
    }

    @Test
    public void testGetJsonResponse() {
        // Send GET request
        Response response = target.request(MediaType.APPLICATION_JSON).get();

        // Check HTTP response status
        assertEquals(200, response.getStatus());

        // Parse JSON response
        String jsonString = response.readEntity(String.class);
        assertNotNull(jsonString);

        // Deserialize JSON to an object (optional, using JSON-B)
        Jsonb jsonb = JsonbBuilder.create();
        ExampleResponse exampleResponse = jsonb.fromJson(jsonString, ExampleResponse.class);

        // Validate the response data
        assertEquals("Hello", exampleResponse.getMessage());
        assertEquals("success", exampleResponse.getStatus());
    }

    // POJO for JSON Response
    public static class ExampleResponse {
        private String message;
        private String status;

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }
    }
}

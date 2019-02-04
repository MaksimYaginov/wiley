package api.steps;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static api.URI.DELAY;
import static io.restassured.RestAssured.given;

public class Delay {

    public Response getDelay(Object delay) {

        return given()
                .contentType(ContentType.JSON)
                .pathParam("delay", delay)
                .when()
                .get("/" + DELAY + "/" + "{delay}");
    }
}

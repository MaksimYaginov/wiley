package api.steps.image;

import io.restassured.http.ContentType;

import java.io.InputStream;

import static api.URI.IMAGE;
import static io.restassured.RestAssured.given;

public class ImageSteps {

    public InputStream getImage(String type, Integer expectedStatusCode) {

        return given()
                .contentType(ContentType.JSON)
                .pathParam("imageType", type.toLowerCase())
                .when()
                .get("/" + IMAGE + "/" + "{imageType}")
                .then().statusCode(expectedStatusCode)
                .extract().body().asInputStream();
    }
}
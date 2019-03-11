package api.steps.delay;

import io.qameta.allure.Step;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import java.util.concurrent.TimeUnit;

import static api.URI.DELAY;
import static io.restassured.RestAssured.given;

public class DelaySteps {

    @Step("Get DelaySteps")
    public Long getDelay(Object delay, Integer expectedStatusCode) {

        Response response = given()
                .contentType(ContentType.JSON)
                .pathParam("delay", delay)
                .when()
                .get("/" + DELAY + "/" + "{delay}")
                .then().statusCode(expectedStatusCode)
                .extract().response();

        return response.getTimeIn(TimeUnit.SECONDS);
    }
}

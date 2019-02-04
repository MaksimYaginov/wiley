package api.base;

import io.restassured.RestAssured;
import org.testng.annotations.BeforeSuite;
import ui.helpers.PropertyManager;

public class BaseApiTest {

    @BeforeSuite(description = "Set base URL")
    public void setBaseURL() {
        RestAssured.baseURI = PropertyManager.getProperty("baseApiUrl");
    }
}

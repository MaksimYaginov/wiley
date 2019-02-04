package api;

import api.base.BaseApiTest;
import api.steps.Delay;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import ui.helpers.PropertyManager;

import java.util.concurrent.TimeUnit;

public class DelayTests extends BaseApiTest {

    private Delay delay = new Delay();
    private Integer MAX_DELAY;

    @DataProvider(name = "delays")
    private Object[][] delays() {
        return new Object[][]{
                {-5}, {5}, {20}
        };
    }

    @BeforeClass(alwaysRun = true)
    public void getMaxDelay() {
        MAX_DELAY = Integer.parseInt(PropertyManager.getProperty("maxDelay"));
    }

    @Test(dataProvider = "delays")
    public void checkDelayTime(Integer delayCount) {
        Response response = delay.getDelay(delayCount);
        Long realDelay = response.getTimeIn(TimeUnit.SECONDS);

        Assert.assertEquals(response.getStatusCode(), HttpStatus.SC_OK);
        Assert.assertTrue(realDelay >= 0 && realDelay <= MAX_DELAY, String.format("Real delay is %s more max delay %s", realDelay, MAX_DELAY));

    }
}

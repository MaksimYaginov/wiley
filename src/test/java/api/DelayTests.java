package api;

import api.base.BaseApiTest;
import api.steps.delay.DelaySteps;
import org.apache.http.HttpStatus;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import ui.helpers.PropertyManager;

public class DelayTests extends BaseApiTest {

    private DelaySteps delaySteps = new DelaySteps();
    private Integer MAX_DELAY;

    @DataProvider(name = "delays")
    private Object[][] delays() {
        return new Object[][]{
                {-5, HttpStatus.SC_OK}, {5, HttpStatus.SC_OK}, {20, HttpStatus.SC_OK},
                {"test", HttpStatus.SC_INTERNAL_SERVER_ERROR}
        };
    }

    @BeforeClass(alwaysRun = true)
    public void getMaxDelay() {
        MAX_DELAY = Integer.parseInt(PropertyManager.getProperty("maxDelay"));
    }

    @Test(dataProvider = "delays")
    public void checkDelayTime(Object delayCount, Integer expectedStatusCode) {
        Long delayTime = delaySteps.getDelay(delayCount, expectedStatusCode);

        if (expectedStatusCode.equals(HttpStatus.SC_OK))
            Assert.assertTrue(delayTime >= 0 && delayTime <= MAX_DELAY, String.format("Real delaySteps is %s more max delaySteps %s", delayTime, MAX_DELAY));
    }
}

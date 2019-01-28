package ui.base;

import io.qameta.allure.Attachment;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.IHookCallBack;
import org.testng.IHookable;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import java.util.concurrent.TimeUnit;

public class BaseUITest implements IHookable {

    protected WebDriver driver;
    protected final static int IMPLICITY_WAIT = 10;
    private final String BASE_URL = "https://www.wiley.com/";

    @BeforeSuite(description = "Set driver settings")
    public void setBrowserSettings() {
        System.setProperty("webdriver.chrome.driver", "src/main/java/resources/chromedriver.exe");

    }

    @BeforeMethod(alwaysRun = true, description = "Open browser")
    public void openBrowser() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(IMPLICITY_WAIT, TimeUnit.SECONDS);
        driver.get(BASE_URL + ui.base.URI.en_us);
        driver.manage().window().maximize();
    }

    @AfterMethod(alwaysRun = true, description = "Close browser")
    public void closeBrowser() {
        driver.quit();
    }

    @Override
    public final void run(final IHookCallBack callBack, final ITestResult testResult) {
        callBack.runTestMethod(testResult);
        if (testResult.getThrowable() != null) {
            takeScreenShot(testResult.getMethod().getMethodName());
        }
    }

    @Attachment(value = "Failure in method {methodName}", type = "image/png")
    private byte[] takeScreenShot(final String methodName) {
        return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
    }
}

package helpers;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class Helper {

    public static WebElement switchToFrame(WebDriver driver, WebElement frame) {
        driver.switchTo().frame(frame);

        return frame;
    }

    public static WebElement moveToElement(WebDriver driver, WebElement webElement) {
        Actions actions = new Actions(driver);
        actions.moveToElement(webElement).build().perform();

        return webElement;
    }
}

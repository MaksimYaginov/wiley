package helpers;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.util.ArrayList;
import java.util.List;

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

    public static List<String> getWebElementsText(WebDriver driver, List<WebElement> webElements) {
        List<String> outputList = new ArrayList<>();

        for (WebElement webElement : webElements)
            outputList.add(webElement.getText());

        return outputList;
    }
}

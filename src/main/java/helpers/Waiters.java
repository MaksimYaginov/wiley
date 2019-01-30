package helpers;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class Waiters {

    private final static int explicitlyWait = Integer.parseInt(PropertyManager.getProperty("explicitlyWait"));

    public static void waitUntilAllElementsVisible(WebDriver driver, List<WebElement> webElements) {
        new WebDriverWait(driver, explicitlyWait).until(ExpectedConditions.visibilityOfAllElements(webElements));
    }

    public static void waitUntilElementVisible(WebDriver driver, WebElement webElement) {
        new WebDriverWait(driver, explicitlyWait).until(ExpectedConditions.visibilityOf(webElement));
    }

    public static void waitUntilElementClickable(WebDriver driver, WebElement webElement) {
        new WebDriverWait(driver, explicitlyWait).until(ExpectedConditions.elementToBeClickable(webElement));
    }
}

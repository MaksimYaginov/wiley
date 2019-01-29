package helpers;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class JavascriptExecutorHelper {

    public static void unfocusElement(WebDriver driver) {
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript(
                "var tmp = document.createElement('input');" +
                        "document.body.appendChild(tmp);" +
                        "tmp.focus();" +
                        "document.body.removeChild(tmp);");
    }

    public static void jsClick(WebDriver driver, WebElement element) {
        ((JavascriptExecutor) driver)
                .executeScript("arguments[0].click()", element);
    }
}

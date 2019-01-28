package helpers;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

public class JavascriptExecutorHelper {

    public static void unfocusElement(WebDriver driver) {
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript(
                "var tmp = document.createElement('input');" +
                        "document.body.appendChild(tmp);" +
                        "tmp.focus();" +
                        "document.body.removeChild(tmp);");
    }
}

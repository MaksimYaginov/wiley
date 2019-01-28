package pageElements;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import static helpers.Waiters.*;

public class CountryForm {

    private WebDriver driver;

    @FindBy(id = "command")
    private WebElement countryForm;

    private By closeCountryFormButton = By.cssSelector("button.close");

    public CountryForm(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @Step("Country form is displayed")
    public boolean countryFormIsDisplayed() {
        return countryForm.isDisplayed();
    }

    @Step("Close country form")
    public void closeCountryForm() {
        waitUntilElementVisible(driver, countryForm);
        countryForm.findElement(closeCountryFormButton).click();
    }
}

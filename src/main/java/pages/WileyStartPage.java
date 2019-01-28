package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import pageElements.CountryForm;
import pageElements.HeaderNavigation;

public class WileyStartPage {

    private WebDriver driver;
    private CountryForm countryForm;
    private HeaderNavigation headerNavigation;

    public WileyStartPage(WebDriver driver) {
        headerNavigation = new HeaderNavigation(driver);
        countryForm = new CountryForm(driver);

        this.driver = driver;
        PageFactory.initElements(driver, this);
        if (countryForm.countryFormIsDisplayed())
            countryForm.closeCountryForm();
    }

    @Step("Get header navigation")
    public HeaderNavigation getHeaderNavigation() {
        return headerNavigation;
    }
}

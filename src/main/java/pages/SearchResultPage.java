package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import pageElements.CountryForm;
import pageElements.HeaderNavigation;

public class SearchResultPage {

    private WebDriver driver;
    private HeaderNavigation headerNavigation;
    private CountryForm countryForm;

    @FindBy(css = "div.search-result-tabs-wrapper")
    private WebElement headerBanner;

    public SearchResultPage(WebDriver driver) {
        headerNavigation = new HeaderNavigation(driver);
        countryForm = new CountryForm(driver);

        this.driver = driver;
        PageFactory.initElements(driver, this);

        if (countryForm.countryFormIsDisplayed())
            countryForm.closeCountryForm();
    }
}

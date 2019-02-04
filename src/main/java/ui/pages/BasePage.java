package ui.pages;

import org.openqa.selenium.WebDriver;
import ui.pageElements.CountryForm;
import ui.pageElements.HeaderNavigation;
import ui.pageElements.SearchRelatedContent;

public class BasePage {

    protected WebDriver driver;
    private CountryForm countryForm;
    HeaderNavigation headerNavigation;
    SearchRelatedContent searchRelatedContent;

    BasePage(WebDriver driver) {
        countryForm = new CountryForm(driver);
        headerNavigation = new HeaderNavigation(driver);
        searchRelatedContent = new SearchRelatedContent(driver);

        this.driver = driver;
        if (countryForm.countryFormIsDisplayed())
            countryForm.closeCountryForm();
    }
}

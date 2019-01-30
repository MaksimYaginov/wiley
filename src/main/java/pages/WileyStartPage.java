package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import pageElements.CountryForm;
import pageElements.HeaderNavigation;
import pageElements.SearchRelatedContent;

public class WileyStartPage {

    private WebDriver driver;
    private CountryForm countryForm;
    private HeaderNavigation headerNavigation;
    private SearchRelatedContent searchRelatedContent;

    public WileyStartPage(WebDriver driver) {
        headerNavigation = new HeaderNavigation(driver);
        countryForm = new CountryForm(driver);
        searchRelatedContent = new SearchRelatedContent(driver);

        this.driver = driver;
        PageFactory.initElements(driver, this);
        if (countryForm.countryFormIsDisplayed())
            countryForm.closeCountryForm();
    }

    @Step("Get header navigation")
    public HeaderNavigation getHeaderNavigation() {
        return headerNavigation;
    }

    @Step("Get search related content")
    public SearchRelatedContent getSearchRelatedContent() {
        return searchRelatedContent;
    }
}

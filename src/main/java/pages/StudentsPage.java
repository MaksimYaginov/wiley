package pages;

import helpers.PropertyManager;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import pageElements.CountryForm;
import pageElements.HeaderNavigation;

import java.util.List;

import static helpers.Waiters.waitUntilAllElementsVisible;

public class StudentsPage {

    private WebDriver driver;
    private HeaderNavigation headerNavigation;
    private CountryForm countryForm;

    @FindBy(css = "div.hero-banner")
    private WebElement headerBanner;

    @FindBy(css = "div.page-content-wrapper")
    private WebElement pageContent;

    private By learnMoreLink = By.xpath("//span[contains(text(),'Learn More')]/ancestor::a");

    public StudentsPage(WebDriver driver) {
        headerNavigation = new HeaderNavigation(driver);
        countryForm = new CountryForm(driver);

        this.driver = driver;
        PageFactory.initElements(driver, this);

        if (countryForm.countryFormIsDisplayed())
            countryForm.closeCountryForm();

        if (!driver.getCurrentUrl().equals(PropertyManager.getProperty("baseUrl") + URI.en_us + "/" + URI.students)) {
            throw new IllegalStateException("Students page is not present");
        }

        if (!headerBanner.isDisplayed()) {
            throw new IllegalStateException("Students page header is not present");
        }
    }

    @Step("Check learn more links")
    public boolean checkLearnMoreLinks(String expectedUrl) {
        boolean result = false;
        List<WebElement> learnMores;

        learnMores = pageContent.findElements(learnMoreLink);
        waitUntilAllElementsVisible(driver, learnMores);

        for (WebElement learnMore : learnMores) {
            result = learnMore.getAttribute("href").contains(expectedUrl);

            if (!result)
                return false;
        }

        return result;
    }
}

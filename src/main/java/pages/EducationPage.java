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

import static helpers.Helper.getWebElementsText;
import static helpers.Waiters.waitUntilAllElementsVisible;

public class EducationPage {

    private WebDriver driver;
    private HeaderNavigation headerNavigation;
    private CountryForm countryForm;

    @FindBy(css = "div.hero-banner")
    private WebElement headerBanner;

    @FindBy(css = "div.container-with-panel")
    private WebElement pageContent;

    private By headerSlogan = By.cssSelector("div.wiley-slogan");
    private By leftSideLinks = By.xpath("//div[@class='side-panel']//ul//a");

    public EducationPage(WebDriver driver) {
        headerNavigation = new HeaderNavigation(driver);
        countryForm = new CountryForm(driver);

        this.driver = driver;
        PageFactory.initElements(driver, this);

        if (countryForm.countryFormIsDisplayed())
            countryForm.closeCountryForm();

        if (!headerBanner.isDisplayed() || !headerBanner.findElement(headerSlogan).getText().trim().equals(PropertyManager.getProperty("educationHeader"))) {
            throw new IllegalStateException("Education page header is not present");
        }
    }

    @Step("Get left side items names")
    public List<String> getLeftSideItemsNames() {
        List<WebElement> leftSideItems = pageContent.findElements(leftSideLinks);
        waitUntilAllElementsVisible(driver, leftSideItems);

        return getWebElementsText(leftSideItems);
    }
}

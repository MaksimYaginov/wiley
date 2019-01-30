package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import pageElements.CountryForm;
import pageElements.HeaderNavigation;

import java.util.ArrayList;
import java.util.List;

import static helpers.Helper.getWebElementsText;
import static helpers.Waiters.waitUntilAllElementsVisible;

public class SearchResultPage {

    private WebDriver driver;
    private HeaderNavigation headerNavigation;
    private CountryForm countryForm;

    @FindBy(css = "section.product-item")
    private List<WebElement> productItems;

    private By productTitle = By.xpath("//*[@class='product-title']");
    private By addToCartButton = By.cssSelector("button.add-to-cart-button");

    public SearchResultPage(WebDriver driver) {
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

    @Step("Get found products titles")
    public List<String> getFoundProductsTitles() {
        List<WebElement> foundProductsTitles = new ArrayList<>();

        for (WebElement productItem : productItems)
            foundProductsTitles.add(productItem.findElement(productTitle));

        waitUntilAllElementsVisible(driver, foundProductsTitles);

        return getWebElementsText(driver, foundProductsTitles);
    }

    @Step("Check add to cart products buttons")
    public boolean checkAddToCartProductsButtons() {
        boolean result = false;
        for (WebElement productItem : productItems) {
            result = productItem.findElements(addToCartButton).size() >= 1;
            if (!result)
                return false;
        }
        return result;
    }
}

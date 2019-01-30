package pageElements;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

import static helpers.Helper.getWebElementsText;
import static helpers.Waiters.waitUntilAllElementsVisible;

public class SearchRelatedContent {

    private WebDriver driver;
    private CountryForm countryForm;

    @FindBy(id = "ui-id-2")
    private WebElement searchRelatedContent;

    private By suggestions = By.cssSelector("section.suggestions div.searchresults-item");
    private By products = By.cssSelector("section.related-content-products-section div.searchresults-item");

    public SearchRelatedContent(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @Step("Get suggestions items")
    public List<String> getSuggestionsItems() {
        List<WebElement> suggestionItems;

        suggestionItems = searchRelatedContent.findElements(suggestions);
        waitUntilAllElementsVisible(driver, suggestionItems);

        return getWebElementsText(suggestionItems);
    }

    @Step("Get products items")
    public List<String> getProductsItems() {
        List<WebElement> productsItems;

        productsItems = searchRelatedContent.findElements(products);
        waitUntilAllElementsVisible(driver, productsItems);

        return getWebElementsText(productsItems);
    }
}

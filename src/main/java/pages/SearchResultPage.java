package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;
import java.util.List;

import static helpers.Helper.getWebElementsText;
import static helpers.Waiters.waitUntilAllElementsVisible;

public class SearchResultPage extends BasePage{
    @FindBy(css = "section.product-item")
    private List<WebElement> productItems;

    private By productTitle = By.xpath("//*[@class='product-title']");
    private By addToCartButton = By.cssSelector("button.add-to-cart-button");

    public SearchResultPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @Step("Get found products titles")
    public List<String> getFoundProductsTitles() {
        List<WebElement> foundProductsTitles = new ArrayList<>();

        for (WebElement productItem : productItems)
            foundProductsTitles.add(productItem.findElement(productTitle));

        waitUntilAllElementsVisible(driver, foundProductsTitles);

        return getWebElementsText(foundProductsTitles);
    }

    @Step("Check add to cart products buttons")
    public boolean checkAddToCartProductsButtons() {
        for (WebElement productItem : productItems) {
            if (!(productItem.findElements(addToCartButton).size() >= 1))
                return false;
        }
        return true;
    }
}

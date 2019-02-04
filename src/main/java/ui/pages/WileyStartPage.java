package ui.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import ui.pageElements.HeaderNavigation;
import ui.pageElements.SearchRelatedContent;

public class WileyStartPage extends BasePage{

    public WileyStartPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
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

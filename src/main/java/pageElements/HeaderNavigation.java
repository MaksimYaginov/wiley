package pageElements;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HeaderNavigation {

    private WebDriver driver;

    @FindBy(css = "div.main-header-navigation")
    private WebElement mainHeaderNavigation;

    private By whoWeServeLink = By.xpath("//a[text()='WHO WE SERVE']");
    private By subjectLink = By.xpath("//a[text()='SUBJECTS']");
    private By aboutLink = By.xpath("//a[text()='ABOUT']");

    public HeaderNavigation(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @Step("Who we serve link is displayed")
    public boolean whoWeServeLinkIsDisplayed() {
        return mainHeaderNavigation.findElement(whoWeServeLink).isDisplayed();
    }

    @Step("Subject link is displayed")
    public boolean subjectLinkIsDisplayed() {
        return mainHeaderNavigation.findElement(subjectLink).isDisplayed();
    }

    @Step("About link is displayed")
    public boolean aboutLinkIsDisplayed() {
        return mainHeaderNavigation.findElement(aboutLink).isDisplayed();
    }
}

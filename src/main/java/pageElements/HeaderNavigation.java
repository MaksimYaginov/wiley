package pageElements;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;
import java.util.List;

import static helpers.Helper.moveToElement;
import static helpers.Waiters.*;

public class HeaderNavigation {

    private WebDriver driver;

    @FindBy(css = "div.main-header-navigation")
    private WebElement mainHeaderNavigation;

    private By whoWeServeLink = By.xpath("//a[text()='WHO WE SERVE']");
    private By subjectLink = By.xpath("//a[text()='SUBJECTS']");
    private By aboutLink = By.xpath("//a[text()='ABOUT']");
    private By whoWeServeSubHeaders = By.xpath("//div[@id='Level1NavNode1']//li/a");

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

    @Step("Move to Who We Serve Link")
    public void moveToWhoWeServeLink() {
        waitUntilElementClickable(driver, mainHeaderNavigation.findElement(whoWeServeLink));
        moveToElement(driver, mainHeaderNavigation.findElement(whoWeServeLink));
    }

    @Step("Get Who We Serve sub-headers")
    public List<String> getWhoWeServeSubHeaders() {
        List<String> subHeaderTitles = new ArrayList<>();
        List<WebElement> subHeaders;

        moveToWhoWeServeLink();
        subHeaders = mainHeaderNavigation.findElement(whoWeServeLink).findElements(whoWeServeSubHeaders);
        waitUntilAllElementsVisible(driver, subHeaders);

        for (WebElement subHeader : subHeaders)
            subHeaderTitles.add(subHeader.getText());

        return subHeaderTitles;
    }
}

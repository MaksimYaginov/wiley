package pageElements;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import pages.EducationPage;
import pages.SearchResultPage;
import pages.StudentsPage;
import pages.WileyStartPage;

import java.util.List;

import static helpers.Helper.getWebElementsText;
import static helpers.Helper.moveToElement;
import static helpers.JavascriptExecutorHelper.jsClick;
import static helpers.Waiters.*;

public class HeaderNavigation {

    private WebDriver driver;

    @CacheLookup
    @FindBy(css = "div.main-header-navigation")
    private WebElement mainHeaderNavigation;

    @CacheLookup
    @FindBy(id = "js-site-search-input")
    private WebElement searchField;

    @CacheLookup
    @FindBy(css = "button.glyphicon-search")
    private WebElement searchButton;

    @CacheLookup
    @FindBy(css = "div.logo")
    private WebElement logo;

    private By whoWeServeLink = By.xpath("//a[text()='WHO WE SERVE']");
    private By subjectLink = By.xpath("//a[text()='SUBJECTS']");
    private By aboutLink = By.xpath("//a[text()='ABOUT']");
    private By whoWeServeSubHeaders = By.xpath("//div[@id='Level1NavNode1']//li/a");
    private By studentsLink = By.xpath("//a[text()='Students']");
    private By educationLink = By.xpath("//a[text()='Education']");

    public HeaderNavigation(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @Step("Search button click")
    private void searchButtonClick() {
        waitUntilElementClickable(driver, searchButton);
        jsClick(driver, searchButton);
    }

    @Step("Set search text")
    public void setSearchText(String text) {
        waitUntilElementClickable(driver, searchField);
        searchField.sendKeys(text);
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
        WebElement whoWeServe = mainHeaderNavigation.findElement(whoWeServeLink);

        waitUntilElementClickable(driver, whoWeServe);
        moveToElement(driver, whoWeServe);
    }

    @Step("Move to subject Link")
    public void moveToSubjectLink() {
        WebElement subject = mainHeaderNavigation.findElement(subjectLink);

        waitUntilElementClickable(driver, subject);
        moveToElement(driver, subject);
    }

    @Step("Get Who We Serve sub-headers")
    public List<String> getWhoWeServeSubHeaders() {
        List<WebElement> subHeaders;

        moveToWhoWeServeLink();
        subHeaders = mainHeaderNavigation.findElement(whoWeServeLink).findElements(whoWeServeSubHeaders);
        waitUntilAllElementsVisible(driver, subHeaders);

        return getWebElementsText(driver, subHeaders);
    }

    @Step("Go to students page")
    public StudentsPage goToStudentsPage() {
        WebElement studentsSubHeader;

        moveToWhoWeServeLink();
        studentsSubHeader = mainHeaderNavigation.findElement(whoWeServeLink).findElement(studentsLink);
        waitUntilElementClickable(driver, studentsSubHeader);
        studentsSubHeader.click();

        return new StudentsPage(driver);
    }

    @Step("Go to education page")
    public EducationPage goToEducationPage() {
        WebElement education;

        moveToSubjectLink();
        education = mainHeaderNavigation.findElement(subjectLink).findElement(educationLink);
        waitUntilElementClickable(driver, education);
        education.click();

        return new EducationPage(driver);
    }

    @Step("Logo click")
    public WileyStartPage logoClick() {
        waitUntilElementClickable(driver, logo);
        jsClick(driver, logo);

        return new WileyStartPage(driver);
    }

    @Step("Search")
    public <T> T search(String text) {
        setSearchText(text);
        searchButtonClick();
        if (text.equals(""))
            return (T) this;
        else
            return (T) new SearchResultPage(driver);
    }
}

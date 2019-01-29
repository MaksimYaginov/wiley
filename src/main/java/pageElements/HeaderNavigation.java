package pageElements;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import pages.EducationPage;
import pages.StudentsPage;

import java.util.List;

import static helpers.Helper.getWebElementsText;
import static helpers.Helper.moveToElement;
import static helpers.JavascriptExecutorHelper.jsClick;
import static helpers.Waiters.*;

public class HeaderNavigation {

    private WebDriver driver;

    @FindBy(css = "div.main-header-navigation")
    private WebElement mainHeaderNavigation;

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
    public void logoClick() {
        waitUntilElementClickable(driver, logo);
        jsClick(driver,logo);
    }
}

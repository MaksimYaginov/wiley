package ui;

import helpers.PropertyManager;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.StudentsPage;
import pages.WileyStartPage;
import ui.base.BaseUITest;

import java.util.Arrays;
import java.util.List;

public class WileySmokeTests extends BaseUITest {

    private WileyStartPage wileyStartPage;
    private StudentsPage studentsPage;

    @Feature("Wiley")
    @Story("WileyStartPage")
    @Test(description = "Check the following links are displayed in the top menu")
    public void checkTheFollowingLinksAreDisplayedInTheTopMenu() {
        wileyStartPage = new WileyStartPage(driver);

        Assert.assertTrue(wileyStartPage.getHeaderNavigation().whoWeServeLinkIsDisplayed()
                && wileyStartPage.getHeaderNavigation().subjectLinkIsDisplayed()
                && wileyStartPage.getHeaderNavigation().aboutLinkIsDisplayed());
    }

    @Feature("Wiley")
    @Story("WileyStartPage")
    @Test(description = "Check items under Who We Serve for sub-header")
    public void checkItemsUnderWhoWeServeForSubHeader() {
        List<String> expectedWhoWeServeSubHeaders = Arrays.asList("Students", "Instructors", "Book Authors", "Professionals",
                "Researchers", "Institutions", "Librarians", "Corporations", "Societies", "Journal Editors", "Journalists", "Government");
        wileyStartPage = new WileyStartPage(driver);

        Assert.assertEquals(wileyStartPage.getHeaderNavigation().getWhoWeServeSubHeaders(), expectedWhoWeServeSubHeaders);
    }

    @Feature("Wiley")
    @Story("WileyStartPage")
    @Test(description = "Check “Students” item")
    public void checkStudentsItem() {
        wileyStartPage = new WileyStartPage(driver);

        studentsPage = wileyStartPage.getHeaderNavigation().goToStudentsPage();

        Assert.assertTrue(studentsPage.checkLearnMoreLinks(PropertyManager.getProperty("wileyplusUrl")));
    }
}

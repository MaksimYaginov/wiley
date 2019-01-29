package ui;

import helpers.PropertyManager;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.EducationPage;
import pages.StudentsPage;
import pages.WileyStartPage;
import ui.base.BaseUITest;

import java.util.Arrays;
import java.util.List;

public class WileySmokeTests extends BaseUITest {

    private WileyStartPage wileyStartPage;
    private StudentsPage studentsPage;
    private EducationPage educationPage;

    @Story("Wiley")
    @Test(description = "Check the following links are displayed in the top menu")
    public void checkTheFollowingLinksAreDisplayedInTheTopMenu() {
        wileyStartPage = new WileyStartPage(driver);

        Assert.assertTrue(wileyStartPage.getHeaderNavigation().whoWeServeLinkIsDisplayed()
                && wileyStartPage.getHeaderNavigation().subjectLinkIsDisplayed()
                && wileyStartPage.getHeaderNavigation().aboutLinkIsDisplayed());
    }

    @Story("Wiley")
    @Test(description = "Check items under Who We Serve for sub-header")
    public void checkItemsUnderWhoWeServeForSubHeader() {
        List<String> expectedWhoWeServeSubHeaders = Arrays.asList("Students", "Instructors", "Book Authors", "Professionals",
                "Researchers", "Institutions", "Librarians", "Corporations", "Societies", "Journal Editors", "Journalists", "Government");
        wileyStartPage = new WileyStartPage(driver);

        Assert.assertEquals(wileyStartPage.getHeaderNavigation().getWhoWeServeSubHeaders(), expectedWhoWeServeSubHeaders);
    }

    @Story("Wiley")
    @Test(description = "Check “Students” item")
    public void checkStudentsItem() {
        wileyStartPage = new WileyStartPage(driver);

        studentsPage = wileyStartPage.getHeaderNavigation().goToStudentsPage();

        Assert.assertTrue(studentsPage.checkLearnMoreLinks(PropertyManager.getProperty("wileyplusUrl")));
    }

    @Story("Wiley")
    @Test(description = "Check Education left side items names")
    public void checkEducationLiftSideItemsNames() {
        List<String> expectedLeftSideItemsNames = Arrays.asList("Information & Library Science", "Education & Public Policy",
                "K-12 General", "Higher Education General", "Vocational Technology", "Conflict Resolution & Mediation (School settings)",
                "Curriculum Tools- General", "Special Educational Needs", "Theory of Education", "Education Special Topics",
                "Educational Research & Statistics", "Literacy & Reading","Classroom Management");
        wileyStartPage = new WileyStartPage(driver);
        educationPage = wileyStartPage.getHeaderNavigation().goToEducationPage();

        Assert.assertEquals(educationPage.getLeftSideItemsNames(), expectedLeftSideItemsNames);
    }
}

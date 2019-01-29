package ui;

import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageElements.HeaderNavigation;
import pages.WileyStartPage;
import ui.base.BaseUITest;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static helpers.Helper.generateString;

public class WileyStartPageSmokeSubMenuTests extends BaseUITest {

    private WileyStartPage wileyStartPage;

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
}

package ui;

import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageElements.HeaderNavigation;
import pages.WileyStartPage;
import ui.base.BaseUITest;

import static helpers.Helper.generateString;

public class WileyStartPageSmokeSubMenuTests extends BaseUITest {

    private WileyStartPage wileyStartPage;

    @Feature("Wiley")
    @Story("WileyStartPage")
    @Test(description = "Smoke sub menu tests")
    public void smokeSubMenuTests() {
        wileyStartPage = new WileyStartPage(driver);

        Assert.assertTrue(wileyStartPage.getHeaderNavigation().whoWeServeLinkIsDisplayed()
                && wileyStartPage.getHeaderNavigation().subjectLinkIsDisplayed()
                && wileyStartPage.getHeaderNavigation().aboutLinkIsDisplayed());
    }
}

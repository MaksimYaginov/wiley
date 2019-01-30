package ui;

import helpers.PropertyManager;
import io.qameta.allure.Story;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.EducationPage;
import pages.SearchResultPage;
import pages.StudentsPage;
import pages.WileyStartPage;
import ui.base.BaseUITest;

import java.util.Arrays;
import java.util.List;

public class WileySmokeTests extends BaseUITest {

    private WileyStartPage wileyStartPage;
    private StudentsPage studentsPage;
    private EducationPage educationPage;
    private SearchResultPage searchResultPage;

    private final String SEARCH_TEXT = "Java";

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
    public void checkWhoWeServeSubHeaders() {
        List<String> expectedWhoWeServeSubHeaders = Arrays.asList("Students", "Instructors", "Book Authors", "Professionals",
                "Researchers", "Institutions", "Librarians", "Corporations", "Societies", "Journal Editors", "Government");
        wileyStartPage = new WileyStartPage(driver);

        Assert.assertEquals(wileyStartPage.getHeaderNavigation().getWhoWeServeSubHeaders(), expectedWhoWeServeSubHeaders);
    }

    @Story("Wiley")
    @Test(description = "Check “Students” item")
    public void checkStudentsPageLearnMoreLinks() {
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
                "Educational Research & Statistics", "Literacy & Reading", "Classroom Management");
        wileyStartPage = new WileyStartPage(driver);
        educationPage = wileyStartPage.getHeaderNavigation().goToEducationPage();

        Assert.assertEquals(educationPage.getLeftSideItemsNames(), expectedLeftSideItemsNames);
    }

    @Story("Wiley")
    @Test(description = "Check logo click")
    public void checkLogoClick() {
        wileyStartPage = new WileyStartPage(driver);
        wileyStartPage.getHeaderNavigation().logoClick();

        Assert.assertEquals(driver.getCurrentUrl(), BASE_URL);
    }

    @Story("Wiley")
    @Test(description = "Check not anything search")
    public void checkNotAnythingSearch() {
        wileyStartPage = new WileyStartPage(driver);
        wileyStartPage.getHeaderNavigation().search("");

        Assert.assertEquals(driver.getCurrentUrl(), BASE_URL);
    }

    @Story("Wiley")
    @Test(description = "Check search related suggestions content")
    public void checkSearchRelatedSuggestionsContent() {
        SoftAssert softAssert = new SoftAssert();

        wileyStartPage = new WileyStartPage(driver);
        wileyStartPage.getHeaderNavigation().setSearchText(SEARCH_TEXT);
        List<String> suggestions = wileyStartPage.getSearchRelatedContent().getSuggestionsItems();

        softAssert.assertEquals(suggestions.size(), 4);
        for (String suggestion : suggestions) {
            softAssert.assertTrue(suggestion.startsWith(SEARCH_TEXT));
            softAssert.assertAll();
        }

    }

    @Story("Wiley")
    @Test(description = "Check search related products content")
    public void checkSearchRelatedProductsContent() {
        List<String> products;
        SoftAssert softAssert = new SoftAssert();

        wileyStartPage = new WileyStartPage(driver);
        wileyStartPage.getHeaderNavigation().setSearchText(SEARCH_TEXT);
        products = wileyStartPage.getSearchRelatedContent().getProductsItems();

        softAssert.assertEquals(products.size(), 5);
        for (String product : products) {
            softAssert.assertTrue(product.contains(SEARCH_TEXT));
            softAssert.assertAll();
        }
    }

    @Story("Wiley")
    @Test(description = "Check search content titles")
    public void checkSearchContent() {
        List<String> productsTitles;
        SoftAssert softAssert = new SoftAssert();

        wileyStartPage = new WileyStartPage(driver);
        searchResultPage = wileyStartPage.getHeaderNavigation().search(SEARCH_TEXT);
        productsTitles = searchResultPage.getFoundProductsTitles();

        softAssert.assertEquals(searchResultPage.getFoundProductsTitles().size(), 10);
        for (String productsTitle : productsTitles) {
            softAssert.assertTrue(productsTitle.contains(SEARCH_TEXT));
            softAssert.assertAll();
        }
    }

    @Story("Wiley")
    @Test(description = "Check search content add cart buttons")
    public void checkSearchContentAddCartButtons() {
        wileyStartPage = new WileyStartPage(driver);

        searchResultPage = wileyStartPage.getHeaderNavigation().search(SEARCH_TEXT);

        Assert.assertTrue(searchResultPage.checkAddToCartProductsButtons(), "Not each title has at least one “Add to Cart” button");
    }

    @Story("Wiley")
    @Test(description = "Check re-search")
    public void checkReSearch() {
        List<String> firstSearchTitles;
        List<String> secondSearchTitles;

        wileyStartPage = new WileyStartPage(driver);
        searchResultPage = wileyStartPage.getHeaderNavigation().search(SEARCH_TEXT);
        firstSearchTitles = searchResultPage.getFoundProductsTitles();
        wileyStartPage = wileyStartPage.getHeaderNavigation().logoClick();
        searchResultPage = wileyStartPage.getHeaderNavigation().search(SEARCH_TEXT);
        secondSearchTitles = searchResultPage.getFoundProductsTitles();

        Assert.assertEquals(firstSearchTitles, secondSearchTitles);
    }
}

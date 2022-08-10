package mvynokur;

import jdk.jfr.Description;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class GoogleTest extends BaseTestRunner {

    @Test
    @Description("Task 1")
    public void verifySearchFunctionalityWorks() {
        Assert.assertTrue(searchResultsPage.getName(1).contains("dogs"));
    }

    @Test
    @Description("Task 2")
    public void verifyNineLinkURLCorrectness() {
        Assert.assertTrue(searchResultsPage.getUrl(9).matches("((http|https)://)(www.)?"
                + "[a-zA-Z0-9@:%._\\+~#?&//=]{2,256}\\.[a-z]"
                + "{2,6}\\b([-a-zA-Z0-9@:%._\\+~#?&//=]*)"));
    }

    @Test
    @Description("Task 3")
    public void verifyNavigationToHomePage() {
        searchResultsPage.openHomePage();
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(homePage.isFeelingLuckyButtonVisible());
        softAssert.assertTrue(homePage.isLanguageBlockVisible());
        softAssert.assertAll();
    }

    @Test
    @Description("Task 4")
    public void verifySearchFunctionalityOnDifferentPages() {
        searchResultsPage.openPage(5);
        Assert.assertTrue(searchResultsPage.getName(1).contains("dogs"));
    }

    @Test
    @Description("Task 5")
    public void verifySearchResultsLinksAmount() {
        Assert.assertTrue(searchResultsPage.getLinksAmount() >= 9);
    }

    @Test
    @Description("Task 6/7")
    public void verifySearchFunctionalityWithDifferentInputs() {
        searchResultsPage.clearSearchField().searchFor("funny kitten");
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(searchResultsPage.getDescriptionText(1).contains("kitten"));
        softAssert.assertFalse(searchResultsPage.getDescriptionText(1).contains("dogs"));
        softAssert.assertAll();
    }

    @Test
    @Description("Task 8")
    public void verifyGoogleLogoIsDisplayed() {
        Assert.assertTrue(searchResultsPage.isLogoVisible());
    }

    @Test
    @Description("Task 9")
    public void verifyNextPageLinkIsDisplayed() {
        Assert.assertTrue(searchResultsPage.isNextPageLinkVisible());
    }

    @Test
    @Description("Task 10")
    public void verifyNextAndPreviousPageLinksAreDisplayed() {
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(searchResultsPage.isNextPageLinkVisible());
        searchResultsPage.openPage(4);
        softAssert.assertTrue(searchResultsPage.isPreviousPageLinkVisible());
        softAssert.assertAll();
    }
}

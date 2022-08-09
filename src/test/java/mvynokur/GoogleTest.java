package mvynokur;

import jdk.jfr.Description;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class GoogleTest extends BaseTestRunner {

    @Test
    @Description("Task 1")
    public void verifyThatSearchFunctionalityWorks() {
        Assert.assertTrue(searchResults.getLinkNameNumber(1).contains("dogs"));
    }

    @Test
    @Description("Task 2")
    public void verifyNineLinkURLCorrectness() {
        Assert.assertTrue(searchResults.getLinkUrlNumber(9).matches("((http|https)://)(www.)?"
                + "[a-zA-Z0-9@:%._\\+~#?&//=]{2,256}\\.[a-z]"
                + "{2,6}\\b([-a-zA-Z0-9@:%._\\+~#?&//=]*)"));
    }

    @Test
    @Description("Task 3")
    public void verifyNavigationToHomePage() {
        homePage.navigateToHomePage();
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(homePage.isFeelingLuckyButtonVisible());
        softAssert.assertTrue(homePage.isLanguageBlockVisible());
        softAssert.assertAll();
    }

    @Test
    @Description("Task 4")
    public void verifySearchFunctionalityOnDifferentPages() {
        homePage.navigateToPage(5);
        Assert.assertTrue(searchResults.getLinkNameNumber(1).contains("dogs"));
    }

    @Test
    @Description("Task 5")
    public void verifySearchResultsLinksAmount() {
        Assert.assertTrue(searchResults.amountOfLinks() >= 9);
    }

    @Test
    @Description("Task 6/7")
    public void verifySearchFunctionalityWithDifferentInputs() {
        homePage.clearSearchField().searchFor("funny kitten");
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(searchResults.getDescriptionTextByLinkNumber(1).contains("kitten"));
        softAssert.assertFalse(searchResults.getDescriptionTextByLinkNumber(1).contains("dogs"));
        softAssert.assertAll();
    }

    @Test
    @Description("Task 8")
    public void verifyLogoIconIsDisplayed() {
        Assert.assertTrue(homePage.isLogoVisible());
    }

    @Test
    @Description("Task 9")
    public void verifyThatNextPageLinkIsDisplayed() {
        Assert.assertTrue(homePage.isNextPageVisible());
    }

    @Test
    @Description("Task 10")
    public void verifyThatPreviousPageLinkIsDisplayed() {
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(homePage.isNextPageVisible());
        homePage.navigateToPage(4);
        softAssert.assertTrue(homePage.isPreviousPageVisible());
        softAssert.assertAll();
    }
}

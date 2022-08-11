package dankomax;

import static org.testng.Assert.*;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;


public class GoogleTest extends TestRunner {
    @Test
    public void verifyFirstSearchResultTitle() {
        String firstTitleText = searchResultsPage.getTitleText(1);
        assertTrue(firstTitleText.contains("dogs"));
    }

    @Test
    public void verifyNinthSearchResultLinkHasValidUrl() {
        String ninthLinkUrl = searchResultsPage.getLinkUrl(9);
        assertTrue(ninthLinkUrl.matches("^((ftp|http|https)://)?www\\..*"));
    }

    @Test
    public void verifyGoogleLogoRedirectsToHomePage() {
        String pageTitle = searchResultsPage
                .openHomePageViaLogo()
                .getPageTitleText();

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(pageTitle, "Google", "Page title should be 'Google'.");
        softAssert.assertTrue(homePage.isSelectLanguageSectionVisible(), "Section for language selection is visible.");
        softAssert.assertTrue(homePage.isFeelingLuckyButtonVisible(), "I'm Feeling Lucky button is visible.");
        softAssert.assertTrue(homePage.isSettingsButtonVisible(), "Settings button is visible.");
        softAssert.assertAll();
    }

    @Test
    public void verifyFirstSearchResultOnFifthPage() {
        String searchResultText = searchResultsPage
                .openSearchResultsPage(5)
                .getText(1);

        assertTrue(searchResultText.contains("dog"));
    }

    @Test
    public void verifySearchResultsQuantityIsSufficient() {
        int resultsQuantity = searchResultsPage
                .getResultsQuantity();

        assertTrue(resultsQuantity >= 9);
    }

    @Test
    public void verifyRepeatSearchResultFirstTitle() {
        String firstResultText = searchResultsPage
                .search("funny kitten")
                .getText(1);

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertFalse(firstResultText.contains("dogs"), "First result title should not contain word 'dogs'.");
        softAssert.assertTrue(firstResultText.contains("kitten"), "First result title should contain word 'kitten'.");
        softAssert.assertAll();
    }

    @Test
    public void verifyGoogleLogoIsDisplayed() {
        boolean googleLogoVisible = searchResultsPage.isGoogleLogoVisible();
        assertTrue(googleLogoVisible, "Google logo is visible.");
    }

    @Test
    public void verifyNextPageLinkIsDisplayed() {
        assertTrue(searchResultsPage.isNextPageLinkVisible(), "'Next' link in pagination should be visible.");
    }

    @Test
    public void verifyNextAnd0PreviousPageLinksAreDisplayed() {
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(searchResultsPage.isNextPageLinkVisible(), "'Next' link in pagination should be visible.");

        searchResultsPage.openSearchResultsPage(4);
        softAssert.assertTrue(searchResultsPage.isPreviousPageLinkVisible(), "'Previous' link in pagination should be visible.");

        softAssert.assertAll();
    }
}
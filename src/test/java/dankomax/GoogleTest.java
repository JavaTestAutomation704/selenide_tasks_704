package dankomax;

import static org.testng.Assert.*;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;


public class GoogleTest extends TestRunner {

    @Test
    public void verifyFirstResultTitle() {
        String firstResultTitle = homePage
                .searchPhrase("funny dogs")
                .searchResultTitleText(1);

        assertTrue(firstResultTitle.toLowerCase().contains("dogs"));
    }

    @Test
    public void verifyNinthResultLinkHrefHasValidURL() {
        String ninthResultLinkHrefValue = homePage
                .searchPhrase("funny dogs")
                .searchResultLinkHrefValue(9);

        assertTrue(ninthResultLinkHrefValue.matches("^((ftp|http|https)://)?www\\..*"));
    }

    @Test
    public void verifyGoogleLogoRedirectToHomePage() {
        String pageTitle = homePage
                .searchPhrase("funny dogs")
                .openHomePage()
                .title();

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(pageTitle, "Google", "Page title should be 'Google'.");
        softAssert.assertTrue(homePage.isSelectLanguageSectionVisible(), "Section for language selection is visible.");
        softAssert.assertTrue(homePage.isFeelingLuckyButtonVisible(), "I'm Feeling Lucky button is visible.");
        softAssert.assertTrue(homePage.isSettingsButtonVisible(), "Settings button is visible.");
        softAssert.assertAll();
    }

    @Test
    public void verifyFirstSearchResultOnFifthPage() {
        String searchResultText = homePage
                .searchPhrase("funny dogs")
                .openSearchResultsPage(5)
                .searchResultText(1);

        System.out.println(searchResultText);
        assertTrue(searchResultText.toLowerCase().contains("dog"));
    }

    @Test
    public void verifySearchResultsQuantityIsSufficient() {
        int searchResultsQuantity = homePage
                .searchPhrase("funny dogs")
                .searchResultCollectionSize();

        assertTrue(searchResultsQuantity >= 9);
    }

    @Test
    public void verifyRepeatSearchFirstResultTitle() {
        String firstResultText = homePage
                .searchPhrase("funny dogs")
                .searchPhrase("funny kitten")
                .searchResultText(1);
        System.out.println(firstResultText);

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertFalse(firstResultText.contains("dogs"), "First result title should not contain word 'dogs'.");
        softAssert.assertTrue(firstResultText.toLowerCase().contains("kitten"), "First result title should contain word 'kitten'.");
        softAssert.assertAll();
    }

    @Test
    public void verifyGoogleLogoIsDisplayed() {
        boolean googleLogoVisible = homePage
                .searchPhrase("funny dogs")
                .isGoogleLogoVisible();

        assertTrue(googleLogoVisible, "Google logo is visible.");
    }

    @Test
    public void verifyNextLinkIsDisplayed() {
        SearchResultsPage searchPage = homePage
                .searchPhrase("funny dogs");

        assertTrue(searchPage.isNextLinkVisible(), "'Next' link in pagination should be visible.");
    }

    @Test
    public void verifyPreviousLinkIsDisplayed() {
        SearchResultsPage searchPage = homePage
                .searchPhrase("funny dogs");

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(searchPage.isNextLinkVisible(), "'Next' link in pagination should be visible.");

        searchPage.openSearchResultsPage(4);
        softAssert.assertTrue(searchPage.isPreviousLinkVisible(), "'Previous' link in pagination should be visible.");

        softAssert.assertAll();
    }
}

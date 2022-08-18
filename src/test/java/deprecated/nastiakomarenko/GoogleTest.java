package deprecated.nastiakomarenko;

import com.codeborne.selenide.WebDriverRunner;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import static org.testng.Assert.assertTrue;

public class GoogleTest extends BaseTestRunner {
    private final String expectedText = "dog";

    @Test
    public void verifyThatMethodSearchesDogs() {
        assertTrue(searchResultsPage.getLinkText(1).contains(expectedText));
    }

    @Test
    public void verifyThatMethodSearchesTheNinthLink() {
        assertTrue(searchResultsPage.getResultLinkAttributeValue(9, "href")
                .matches("[(http(s)?):\\/\\/(www\\.)?a-zA-Z0-9@:%._\\+~#=]" +
                        "{2,256}\\.[a-z]{2,6}\\b([-a-zA-Z0-9@:%_\\+.~#?&//=]*)"));
    }

    @Test
    public void verifyThatGooglePageIsOpen() {
        searchResultsPage.openGooglePageViaLogo();
        String currentUrl = WebDriverRunner.getWebDriver().getCurrentUrl();
        assertTrue(currentUrl.contains("https://www.google.com/"), "Google page didn't open");
    }

    @Test
    public void verifyThatFifthPageHasText() {
        searchResultsPage.openPage(5);
        assertTrue(searchResultsPage.getLinkText(1).contains(expectedText));
    }

    @Test
    public void verifyThatNineLinksAreDisplayed() {
        searchResultsPage.openPage(5);
        assertTrue(searchResultsPage.getResultLinksSize() >= 9);
    }

    @Test
    public void verifyThatMethodContainsText() {
        searchResultsPage.clearSearchField()
                .search("funny kitten");
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertFalse(searchResultsPage.getLinkText(1).contains(expectedText));
        softAssert.assertTrue(searchResultsPage.getLinkText(1).contains("kitten"));
        softAssert.assertAll();
    }

    @Test
    public void verifyThatGoogleLogoIsVisible() {
        assertTrue(searchResultsPage.isLogoVisible());
    }

    @Test
    public void verifyThatNextLinkIsVisible() {
        assertTrue(searchResultsPage.isNextPageLinkVisible());
    }

    @Test
    public void verifyThatLinksAreVisible() {
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(searchResultsPage.isNextPageLinkVisible());
        searchResultsPage.openPage(5);
        softAssert.assertTrue(searchResultsPage.isPreviousPageLinkDisplayed());
        softAssert.assertAll();
    }
}
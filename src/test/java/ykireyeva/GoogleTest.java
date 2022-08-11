package ykireyeva;

import jdk.jfr.Description;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class GoogleTest extends BaseTestRunner {
    private final String expectedText = "dogs";

    @Test
    @Description("Test 1")
    public void verifyLinkText() {
        assertTrue(searchResultsPage.getLinkText(1).contains(expectedText));
    }

    @Test
    @Description("Test 2")
    public void verifyHrefUrlValue() {
        assertTrue(searchResultsPage.getResultLinkAttributeValue(9, "href")
                .matches("[(http(s)?):\\/\\/(www\\.)?a-zA-Z0-9@:%._\\+~#=]" +
                        "{2,256}\\.[a-z]{2,6}\\b([-a-zA-Z0-9@:%_\\+.~#?&//=]*)"));
    }

    @Test
    @Description("Test 3")
    public void verifyGooglePageIsOpen() {
        searchResultsPage.openGooglePageViaLogo();
        assertEquals(homePage.getPageTitleText(), "Google");
        assertTrue(homePage.isGmailLinkVisible());
    }

    @Test
    @Description("Test 4")
    public void verifyLinkTextOnSelectedPage() {
        searchResultsPage.openPage(5);
        assertTrue(searchResultsPage.getLinkText(1).contains(expectedText));
    }

    @Test
    @Description("Test 5")
    public void verifyResultLinksAmount() {
        searchResultsPage.openPage(5);
        assertTrue(searchResultsPage.getResultLinksSize() >= 9);
    }

    @Test
    @Description("Test 6")
    public void verifyLinkTextWithDifferentInputs() {
        searchResultsPage.clearSearchField()
                .search("funny kitten");
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertFalse(searchResultsPage.getLinkText(1).contains(expectedText));
        softAssert.assertTrue(searchResultsPage.getLinkText(1).contains("kitten"));
        softAssert.assertAll();
    }

    @Test
    @Description("Test 8")
    public void verifyGoogleLogoIsVisible() {
        assertTrue(searchResultsPage.isLogoVisible());
    }

    @Test
    @Description("Test 9")
    public void verifyNextPageLinkIsVisible() {
        assertTrue(searchResultsPage.isNextPageLinkVisible());
    }

    @Test
    @Description("Test 10")
    public void verifyPreviousPageLinkIsVisible() {
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(searchResultsPage.isNextPageLinkVisible());
        searchResultsPage.openPage(5);
        softAssert.assertTrue(searchResultsPage.isPreviousPageLinkVisible());
        softAssert.assertAll();
    }
}

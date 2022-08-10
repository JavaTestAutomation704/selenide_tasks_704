package ykireyeva;

import jdk.jfr.Description;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class GoogleTest extends BaseTestRunner {

    @Test
    @Description("Test 1")
    public void verifyLinkText() {
        assertTrue(searchResultsPage.getLinkText(1).contains("dogs"));
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
        searchResultsPage.openGooglePage();
        assertEquals(searchPage.getPageTitleText(), "Google");
        assertTrue(searchPage.isGmailLinkDisplayed());
    }

    @Test
    @Description("Test 4")
    public void verifyLinkTextOnSelectedPage() {
        searchResultsPage.openPage(5);
        assertTrue(searchResultsPage.getLinkText(1).contains("dog"));
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
        softAssert.assertFalse(searchResultsPage.getLinkText(1).contains("dogs"));
        softAssert.assertTrue(searchResultsPage.getLinkText(1).contains("kitten"));
        softAssert.assertAll();

    }

    @Test
    @Description("Test 8")
    public void verifyGoogleLogoIsDisplayed() {
        assertTrue(searchResultsPage.isLogoDisplayed());
    }

    @Test
    @Description("Test 9")
    public void verifyNextPageLinkIsDisplayed() {
        assertTrue(searchResultsPage.isNextPageLinkDisplayed());
    }

    @Test
    @Description("Test 10")
    public void verifyPreviousPageLinkIsDisplayed() {
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(searchResultsPage.isNextPageLinkDisplayed());
        searchResultsPage.openPage(5);
        softAssert.assertTrue(searchResultsPage.isPreviousPageLinkDisplayed());
        softAssert.assertAll();
    }
}

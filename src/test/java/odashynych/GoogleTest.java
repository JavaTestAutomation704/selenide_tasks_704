package odashynych;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import static org.testng.Assert.assertTrue;

public class GoogleTest extends TestRunner {
    @Test
    public void verifyFirstLinkContainDogsTest() {
        boolean result = resultPage.getText(1).contains("dogs");
        assertTrue(result, "Text doesn't contain \"dogs\"");
    }

    @Test
    public void verifyValidNineUrl() {
        assertTrue(resultPage.getUrl(9)
                .matches("^https?:\\/\\/(?:www\\.)?"
                        + "[-a-zA-Z0-9@:%._\\+~#=]{1,256}\\"
                        + ".[a-zA-Z0-9()]{1,6}\\b(?:[-a-zA-Z0-9()@:%_\\+.~#?&\\/=]*)$"),
                "Nine URL is invalid");
    }

    @Test
    public void verifyGoogleHomePageIsOpen() {
        boolean result = resultPage.openHomePage().isLanguageComponentVisible();
        assertTrue(result, "Home page isn't open");
    }

    @Test
    public void verifyFirstLinkContainDogsTestOn5Page() {
        boolean result = resultPage
                .openPage(5)
                .getText(1)
                .contains("dogs");
        assertTrue(result, "Text doesn't contain \"dogs\"");
    }

    @Test
    public void verify9ResultsLinksDisplayed() {
        assertTrue(resultPage.getLinksAmount() >= 9, "Links are less than 9");
    }

    @Test
    public void verifyFirstLinkContainCurrentSearchingWord() {
        String linkText = resultPage
                .clearInputField()
                .search("funny kitten")
                .getText(1);

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(linkText.contains("kitten"), "Text doesn't contain \"kitten\"");
        softAssert.assertFalse(linkText.contains("dogs"), "Text contains \"dogs\"");
        softAssert.assertAll();
    }

    @Test
    public void verifyGoogleLogoDisplayed() {
        assertTrue(resultPage.isGoogleLogoDisplayed(), "Google logo isn't display");
    }

    @Test
    public void verifyNextPageLinkIsDisplayed() {
        assertTrue(resultPage.isNextPageLinkDisplayed(), "Next page button isn't display");
    }

    @Test
    public void verifyNextAndPreviousLinksAreDisplayed() {
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(resultPage.isNextPageLinkDisplayed(), "Next link page isn't visible");
        resultPage.openPage(4);
        softAssert.assertTrue(resultPage.isPreviousPageLinkDisplayed(), "Previous link page isn't visible");
    }
}

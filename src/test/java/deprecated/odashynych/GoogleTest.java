package deprecated.odashynych;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import static org.testng.Assert.assertTrue;

public class GoogleTest extends TestRunner {

    private final String expectedTerm = "dog";

    @Test
    public void verifyFirstLinkContainDogsTest() {
        assertTrue(resultPage.getText(1).contains(expectedTerm), "Text doesn't contain " + expectedTerm);
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
        assertTrue(resultPage.openGooglePageViaLogo().isLanguageComponentVisible(), "Home page isn't open");
    }

    @Test
    public void verifyFirstLinkContainDogsTestOn5Page() {
        assertTrue(resultPage
                .openSearchPage(5)
                .getText(1)
                .contains(expectedTerm), "Text doesn't contain " + expectedTerm);
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
        softAssert.assertFalse(linkText.contains(expectedTerm), "Text contains " + expectedTerm);
        softAssert.assertAll();
    }

    @Test
    public void verifyGoogleLogoDisplayed() {
        assertTrue(resultPage.isGoogleLogoVisible(), "Google logo isn't display");
    }

    @Test
    public void verifyNextPageLinkIsDisplayed() {
        assertTrue(resultPage.isNextPageLinkVisible(), "Next page button isn't display");
    }

    @Test
    public void verifyNextAndPreviousLinksAreDisplayed() {
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(resultPage.isNextPageLinkVisible(), "Next link page isn't visible");
        resultPage.openSearchPage(4);
        softAssert.assertTrue(resultPage.isPreviousPageLinkVisible(), "Previous link page isn't visible");
    }
}

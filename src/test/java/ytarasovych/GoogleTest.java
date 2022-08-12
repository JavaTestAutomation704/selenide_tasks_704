package ytarasovych;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import static org.testng.Assert.assertTrue;

public class GoogleTest extends BaseMethods {
    private final String searchTerm = "funny dogs";
    private final String expectedTerm = "dog";

    @Test
    public void verifySearchFirstLink() {
        String linkText = googleHomePage
                .search(searchTerm)
                .getLinkText(1);
        assertTrue(linkText.contains(expectedTerm));
    }

    @Test
    public void verifyNinthLinkUrlIsValid() {
        boolean isUrlValid = googleHomePage
                .search(searchTerm)
                .getUrl(9)
                .matches("(ftp|http|https)://(www.)?.*");
        assertTrue(isUrlValid);
    }

    @Test
    public void verifyGooglePageIsOpen() {
        boolean isHomePageOpen = googleHomePage
                .search(searchTerm)
                .openHomePageViaLogo()
                .isOpen();
        assertTrue(isHomePageOpen);
    }

    @Test
    public void verifyFirstLinkInFifthPage() {
        String linkText = googleHomePage
                .search(searchTerm)
                .openPage(5)
                .getLinkText(1);
        assertTrue(linkText.contains(expectedTerm));
    }

    @Test
    public void verifyNineLinksAreDisplayed() {
        int linksAmount = googleHomePage
                .search(searchTerm)
                .getLinksAmount();
        assertTrue(linksAmount >= 9);
    }

    @Test
    public void verifyFirstLinkDifferentInput() {
        String linkText = googleHomePage
                .search(searchTerm)
                .clearSearchField()
                .search("funny kitten")
                .getLinkText(1);
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertFalse(linkText.contains(expectedTerm));
        softAssert.assertTrue(linkText.contains("kitten"));
        softAssert.assertAll();
    }

    @Test
    public void verifyLogoIsDisplayed() {
        boolean isGoogleLogoVisible = googleHomePage
                .search(searchTerm)
                .isGoogleLogoVisible();
        assertTrue(isGoogleLogoVisible);
    }

    @Test
    public void verifyLinkNextPageIsDisplayed() {
        boolean isNextLinkVisible = googleHomePage
                .search(searchTerm)
                .isNextLinkVisible();
        assertTrue(isNextLinkVisible);
    }

    @Test
    public void verifyLinkNextAndPreviousPageAreDisplayed() {
        GoogleSearchResultPage googleSearchPage = googleHomePage.search(searchTerm);
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(googleSearchPage.isNextLinkVisible());
        softAssert.assertTrue(googleSearchPage
                .openPage(4)
                .isPreviousLinkVisible());
        softAssert.assertAll();
    }
}
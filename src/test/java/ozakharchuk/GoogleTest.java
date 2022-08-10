package ozakharchuk;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class GoogleTest extends GoogleTestRunner {

    @Test
    public void verifyFirstLinkNameContainsDogs() {
        String linkName = googleSearchResultsPage.getResultName(1);
        Assert.assertTrue(linkName.contains("dogs"), "Link name does not contain word 'dog'!");
    }

    @Test
    public void verifyNinthLinkValid() {
        boolean isUrlValid = googleSearchResultsPage.getResultUrl(8).matches("((http|https)://)(www.)?"
                + "[a-zA-Z0-9@:%._\\+~#?&//=]{2,256}\\.[a-z]"
                + "{2,6}\\b([-a-zA-Z0-9@:%._\\+~#?&//=]*)");
        Assert.assertTrue(isUrlValid, "Url is not valid!");
    }

    @Test
    public void verifyGoogleHomePageIsOpen() {
        boolean isGoogleHomePageOpen = googleSearchResultsPage
                .openGooglePage()
                .isLanguageBlockVisible();
        Assert.assertTrue(isGoogleHomePageOpen, "Google home page is not open!");
    }

    @Test
    public void verifyFirstLinkNameContainsDogsFifthPage() {
        String linkName = googleSearchResultsPage
                .openResultPage(5)
                .getResultName(1).toLowerCase();
        Assert.assertTrue(linkName.contains("dog"), "Link name does not contain word 'dog'!");
    }

    @Test
    public void verifyLinkQuantity() {
        int numberOfLinks = googleSearchResultsPage.getResultLinksNumber();
        Assert.assertTrue(numberOfLinks >= 9, "Links quantity smaller than 9!");
    }

    @Test
    public void verifyFirstLinkContainsKitten() {
        SoftAssert softAssert = new SoftAssert();
        String linkName = googlePage
                .searchForText("funny kitten")
                .getResultName(1)
                .toLowerCase();
        softAssert.assertFalse(linkName.contains("dog"), "Link name contains word 'dog'!");
        softAssert.assertTrue(linkName.contains("kitten"), "Link name does not contain word 'kitten'!");
        softAssert.assertAll();
    }

    @Test
    public void verifyGoogleLogoIsDisplayed() {
        boolean isGoogleLogoVisible = googleSearchResultsPage.isGoogleLogoVisible();
        Assert.assertTrue(isGoogleLogoVisible, "Logo is not visible!");
    }

    @Test
    public void verifyNextLinkIsDisplayed() {
        boolean isNextPageButtonVisible = googleSearchResultsPage.isNextPageLinkVisible();
        Assert.assertTrue(isNextPageButtonVisible, "Next page link is not visible!");
    }

    @Test
    public void verifyNextAndPreviousLinkAreDisplayed() {
        SoftAssert softAssert = new SoftAssert();
        boolean isNextPageButtonVisible = googleSearchResultsPage.isNextPageLinkVisible();
        softAssert.assertTrue(isNextPageButtonVisible, "Next page link is not visible!");
        googleSearchResultsPage.openResultPage(4);
        boolean isPreviousPageButtonVisible = googleSearchResultsPage.isPreviousPageLinkVisible();
        softAssert.assertTrue(isPreviousPageButtonVisible, "Previous page link is not visible!");
        softAssert.assertAll();
    }
}

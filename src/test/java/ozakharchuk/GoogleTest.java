package ozakharchuk;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class GoogleTest extends GoogleTestRunner {

    private final String expectedText = "dogs";

    @Test
    public void verifyFirstLinkName() {
        String linkName = searchResultsPage.getResultName(1);
        Assert.assertTrue(linkName.contains(expectedText), "Link name does not contain word 'dog'!");
    }

    @Test
    public void verifyNinthLinkValid() {
        boolean isUrlValid = searchResultsPage.getResultUrl(8).matches("((http|https)://)(www.)?"
                + "[a-zA-Z0-9@:%._\\+~#?&//=]{2,256}\\.[a-z]"
                + "{2,6}\\b([-a-zA-Z0-9@:%._\\+~#?&//=]*)");
        Assert.assertTrue(isUrlValid, "Url is not valid!");
    }

    @Test
    public void verifyHomePageIsOpenViaLogo() {
        boolean isGoogleHomePageOpen = searchResultsPage
                .openHomePageViaLogo()
                .isLanguageBlockVisible();
        Assert.assertTrue(isGoogleHomePageOpen, "Google home page is not open!");
    }

    @Test
    public void verifyFirstLinkNameFifthPage() {
        String linkName = searchResultsPage
                .openPage(5)
                .getResultName(1).toLowerCase();
        Assert.assertTrue(linkName.contains(expectedText), "Link name does not contain word 'dog'!");
    }

    @Test
    public void verifyLinkQuantity() {
        int numberOfLinks = searchResultsPage.getLinksAmount();
        Assert.assertTrue(numberOfLinks >= 9, "Links quantity smaller than 9!");
    }

    @Test
    public void verifyFirstLinkWithDifferentInput() {
        SoftAssert softAssert = new SoftAssert();
        String linkName = homePage
                .searchFor("funny kitten")
                .getResultName(1)
                .toLowerCase();
        softAssert.assertFalse(linkName.contains(expectedText), "Link name contains word 'dog'!");
        softAssert.assertTrue(linkName.contains("kitten"), "Link name does not contain word 'kitten'!");
        softAssert.assertAll();
    }

    @Test
    public void verifyGoogleLogoIsVisible() {
        boolean isGoogleLogoVisible = searchResultsPage.isGoogleLogoVisible();
        Assert.assertTrue(isGoogleLogoVisible, "Logo is not visible!");
    }

    @Test
    public void verifyNextLinkIsVisible() {
        boolean isNextPageButtonVisible = searchResultsPage.isNextPageLinkVisible();
        Assert.assertTrue(isNextPageButtonVisible, "Next page link is not visible!");
    }

    @Test
    public void verifyNextAndPreviousLinksAreVisible() {
        SoftAssert softAssert = new SoftAssert();
        boolean isNextPageButtonVisible = searchResultsPage.isNextPageLinkVisible();
        softAssert.assertTrue(isNextPageButtonVisible, "Next page link is not visible!");
        searchResultsPage.openPage(4);
        boolean isPreviousPageButtonVisible = searchResultsPage.isPreviousPageLinkVisible();
        softAssert.assertTrue(isPreviousPageButtonVisible, "Previous page link is not visible!");
        softAssert.assertAll();
    }
}

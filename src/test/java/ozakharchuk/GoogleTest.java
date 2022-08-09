package ozakharchuk;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import static com.codeborne.selenide.WebDriverConditions.*;
import static com.codeborne.selenide.Selenide.*;

public class GoogleTest extends GoogleTestRunner {

    @Test
    public void verifyFirstLinkNameContainsDogs() {
        String linkName = googleSearchResultsPage
                .getResultLinkName(1)
                .toLowerCase();
        Assert.assertTrue(linkName.contains("dogs"), "Link name is : " + googleSearchResultsPage.getResultLinkName(1));
    }

    @Test
    public void verifyNinthLinkValid() {
        String linkUrl = googleSearchResultsPage.getResultLinkUrl(8);
        googleSearchResultsPage.openLink(8);
        webdriver().shouldHave(url(linkUrl));
    }

    @Test
    public void verifyGoogleHomePageIsOpen() {
        boolean isGoogleHomePageOpen = googleSearchResultsPage
                .openHomePage()
                .isLanguageBlockVisible();
        Assert.assertTrue(isGoogleHomePageOpen);
    }

    @Test
    public void verifyFirstLinkNameContainsDogsFifthPage() {
        String linkName = googleSearchResultsPage
                .chooseNumberOfResultPage(5)
                .getResultLinkName(1).toLowerCase();
        Assert.assertTrue(linkName.contains("dog"), "Link name is : " + googleSearchResultsPage.getResultLinkName(1));
    }

    @Test
    public void verifyAtLeastNineLinks() {
        int numberOfLinks = googleSearchResultsPage.getResultLinksNumber();
        Assert.assertTrue(numberOfLinks >= 9);
    }

    @Test
    public void verifyFirstLinkContainsKitten() {
        String linkName = googleSearchResultsPage
                .openHomePage()
                .searchForText("funny kitten")
                .getResultLinkName(1)
                .toLowerCase();
        Assert.assertTrue(linkName.contains("kitten"), "Link name is : " + googleSearchResultsPage.getResultLinkName(1));
    }

    @Test
    public void verifyGoogleLogoIsDisplayed() {
        boolean isGoogleLogoVisible = googleSearchResultsPage.isGoogleLogoVisible();
        Assert.assertTrue(isGoogleLogoVisible);
    }

    @Test
    public void verifyNextLinkIsDisplayed() {
        boolean isNextPageButtonVisible = googleSearchResultsPage.isNextPageButtonVisible();
        Assert.assertTrue(isNextPageButtonVisible);
    }

    @Test
    public void verifyNextAndPreviousLinkIsDisplayed() {
        SoftAssert softAssert = new SoftAssert();
        boolean isNextPageButtonVisible = googleSearchResultsPage.isNextPageButtonVisible();
        softAssert.assertTrue(isNextPageButtonVisible);
        googleSearchResultsPage.chooseNumberOfResultPage(4);
        boolean isPreviousPageButtonVisible = googleSearchResultsPage.isPreviousPageButtonVisible();
        softAssert.assertTrue(isPreviousPageButtonVisible);
        softAssert.assertAll();
    }
}

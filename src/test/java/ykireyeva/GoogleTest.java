package ykireyeva;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import static com.codeborne.selenide.Selenide.webdriver;
import static com.codeborne.selenide.WebDriverConditions.url;
import static org.testng.Assert.assertTrue;

public class GoogleTest extends BaseTestRunner {

    @Test
    public void oneVerifyLinkText() {
        assertTrue(resultsPage.getLinkOwnText(0).contains("dogs"));
    }

    @Test
    public void twoVerifyHrefUrl() {
        String valueOfHref = resultsPage.getAttributeValueOfResultLink(9, "href");
        resultsPage.openLink(9);
        webdriver().shouldHave(url(valueOfHref));
    }

    @Test
    public void threeVerifyGoogleHomePageIsOpen() {
        resultsPage.openGoogleSearchPage();
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(searchPage.getTitleOwnText(), "Google");
        softAssert.assertTrue(searchPage.gmailLinkIsDisplayed());
        softAssert.assertFalse(searchPage.submitButtonOnSearchFieldIsDisplayed());
        softAssert.assertAll();
    }

    @Test
    public void fourVerifyLinkText() {
        resultsPage.goToPage(5);
        assertTrue(resultsPage.getLinkOwnText(0).contains("dog"));
    }

    @Test
    public void fiveVerifyNumberOfResultLinks() {
        resultsPage.goToPage(5);
        assertTrue(resultsPage.getResultLinksSize() >= 9);
    }

    @Test
    public void sixVerifyLinkText() {
        resultsPage.clearSearchField()
                .search("funny kitten");
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertFalse(resultsPage.getLinkOwnText(0).contains("dogs"));
        softAssert.assertTrue(resultsPage.getLinkOwnText(0).contains("kitten"));
        softAssert.assertAll();

    }

    @Test
    public void eightVerifyGoogleLogoIsDisplayed() {
        assertTrue(resultsPage.logoIsDisplayed());
    }

    @Test
    public void nineVerifyNextBtnIsDisplayed() {
        assertTrue(resultsPage.nextPageButtonIsDisplayed());
    }

    @Test
    public void tenVerifyPreviousBtnIsDisplayed() {
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(resultsPage.nextPageButtonIsDisplayed());
        resultsPage.goToPage(5);
        softAssert.assertTrue(resultsPage.prevPageButtonIsDisplayed());
        softAssert.assertAll();
    }
}

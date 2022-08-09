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
        softAssert.assertTrue(searchPage.gmailLinkIsEnabled());
        softAssert.assertFalse(searchPage.submitButtonOnSearchFieldIsEnabled());
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
        assertTrue(resultsPage.logoIsEnabled());
    }

    @Test
    public void nineVerifyNextBtnIsDisplayed() {
        assertTrue(resultsPage.nextPageButtonIsEnabled());
    }

    @Test
    public void tenVerifyPreviousBtnIsDisplayed() {
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(resultsPage.nextPageButtonIsEnabled());
        resultsPage.goToPage(5);
        softAssert.assertTrue(resultsPage.prevPageButtonIsEnabled());
        softAssert.assertAll();
    }
}

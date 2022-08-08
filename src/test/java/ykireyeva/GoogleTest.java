package ykireyeva;

import com.codeborne.selenide.Condition;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Selenide.webdriver;
import static com.codeborne.selenide.WebDriverConditions.url;

public class GoogleTest extends BaseTestRunner {

    @Test
    public void oneVerifyLinkText() {
        resultsPage.resultLinkShouldHaveOwnText(0, "dogs");
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
        searchPage.titleShouldHave("Google").gmailLinkShouldBe(Condition.enabled);
        resultsPage.submitButtonOnSearchFieldShouldNot(Condition.exist);
    }

    @Test
    public void fourVerifyLinkText() {
        resultsPage.goToPage(5).resultLinkShouldHaveOwnText(0, "dog");
    }

    @Test
    public void fiveVerifyNumberOfResultLinks() {
        resultsPage.goToPage(5).resultLinksShouldHaveSizeGreaterThanOrEqual(9);
    }

    @Test
    public void sixVerifyLinkText() {
        resultsPage.clearSearchField()
                .search("funny kitten")
                .resultLinkShouldNotHaveOwnText(0, "dogs")
                .resultLinkShouldHaveOwnText(0, "kitten");
    }

    @Test
    public void eightVerifyGoogleLogoIsDisplayed() {
        resultsPage.googleLogoShouldBe(Condition.enabled);
    }

    @Test
    public void nineVerifyNextBtnIsDisplayed() {
        resultsPage.nextPageButtonShouldBe(Condition.enabled);
    }

    @Test
    public void tenVerifyPreviousBtnIsDisplayed() {
        resultsPage.nextPageButtonShouldBe(Condition.enabled)
                .goToPage(5)
                .prevPageButtonShouldBe(Condition.enabled);
    }
}

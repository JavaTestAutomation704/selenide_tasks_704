package ykireyeva;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Condition;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Selenide.webdriver;
import static com.codeborne.selenide.WebDriverConditions.url;

public class GoogleTest extends BaseTestRunner {

    @Test
    public void oneVerifyLinkText() {
        resultsPage.getResultLinksTitles().get(0).shouldHave(Condition.ownText("dogs"));
    }

    @Test
    public void twoVerifyHrefUrl() {
        String valueOfHref = resultsPage.getResultLinks().get(8).getAttribute("href");
        resultsPage.getResultLinks().get(8).click();
        webdriver().shouldHave(url(valueOfHref));
    }

    @Test
    public void threeVerifyGoogleHomePageIsOpen() {
        resultsPage.getGoogleLogo().click();
        searchPage.getPageTitle().shouldHave(Condition.exactOwnText("Google"));
        searchPage.getGmailLink().shouldBe(Condition.enabled);
        resultsPage.getSubmitButtonOnSearchField().shouldNot(Condition.exist);
    }

    @Test
    public void fourVerifyLinkText() {
        resultsPage.getPageNumbersToOpen().get(3).click();
        resultsPage.getResultLinksTitles().get(0).shouldHave(Condition.ownText("dog"));
    }

    @Test
    public void fiveVerifyNumberOfResultLinks() {
        resultsPage.getPageNumbersToOpen().get(3).click();
        resultsPage.getResultLinks().shouldHave(CollectionCondition.sizeGreaterThanOrEqual(9));
    }

    @Test
    public void sixVerifyLinkText() {
        resultsPage.getClearSearchFieldButton().click();
        resultsPage.search("funny kitten");
        resultsPage.getResultLinksTitles().get(0)
                .shouldNotHave(Condition.ownText("dogs"))
                .shouldHave(Condition.ownText("kitten"));
    }

    @Test
    public void eightVerifyGoogleLogoIsDisplayed() {
        resultsPage.getGoogleLogo().shouldBe(Condition.enabled);
    }

    @Test
    public void nineVerifyNextBtnIsDisplayed() {
        resultsPage.getNextPageButton().shouldBe(Condition.enabled);
    }

    @Test
    public void tenVerifyPreviousBtnIsDisplayed() {
        resultsPage.getNextPageButton().shouldBe(Condition.enabled);
        resultsPage.getPageNumbersToOpen().get(3).click();
        resultsPage.getPrevPageButton().shouldBe(Condition.enabled);
    }
}

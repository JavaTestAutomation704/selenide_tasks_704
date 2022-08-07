package olenka;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.WebDriverRunner;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverConditions.url;
import static org.testng.Assert.assertTrue;

public class GoogleTest extends TestRunner {
    @Test
    public void verifyFirstLinkContainDogsTest() {
        resultPage.linkContainsText(1, "dogs");
    }

    @Test
    public void verifyValidNineUrl() {
        String nineUrl = resultPage.getLinkHref(9);
        resultPage.openLink(9);
        webdriver().shouldHave(url(nineUrl));
    }

    @Test
    public void verifyGoogleHomePageIsOpen() {
        resultPage.clickOnLogo();
        String currentUrl = WebDriverRunner.getWebDriver().getCurrentUrl();
        assertTrue(currentUrl.contains("https://www.google.com/"));
    }

    @Test
    public void verifyFirstLinkContainDogsTestOn5Page() {
        resultPage.goToPage(5)
                .linkContainsText(1, "dogs");
    }

    @Test
    public void verify9ResultsLinksDisplayed() {
        resultPage.getAllLinks().shouldHave(CollectionCondition.sizeGreaterThanOrEqual(9));
    }

    @Test
    public void verifyFirstLinkContainCurrentSearchingWord() {
        resultPage
                .clearInputField()
                .search("funny kitten")
                .linkContainsText(1, "kitten")
                .linkNotContainsText(1, "kitten");
    }

    @Test
    public void verifyGoogleLogoDisplayed() {
        resultPage.getLogo().isDisplayed();
    }

    @Test
    public void verifyNextLinkDisplayed() {
        resultPage.getNextPage().isDisplayed();
    }

    @Test
    public void verifyPreviousLinkDisplayed() {
        resultPage.getNextPage().isDisplayed();
        resultPage.goToPage(4);
        resultPage.getPreviousPage().isDisplayed();
    }
}

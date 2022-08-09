package odashynych;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.WebDriverRunner;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverConditions.url;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

public class GoogleTest extends TestRunner {
    @Test
    public void verifyFirstLinkContainDogsTest() {
        assertTrue(resultPage.getTextFromLinkNumber(1).contains("dogs"), "Text doesn't contain \"dogs\"");
    }

    @Test
    public void verifyValidNineUrl() {
        String nineUrl = resultPage.getLinkNumberHref(9);
        resultPage.openLink(9);
        webdriver().shouldHave(url(nineUrl));
    }

    @Test
    public void verifyGoogleHomePageIsOpen() {
        resultPage.openGoogleLogo();
        String currentUrl = WebDriverRunner.getWebDriver().getCurrentUrl();
        assertTrue(currentUrl.contains("https://www.google.com/"), "Home page isn't open");
    }

    @Test
    public void verifyFirstLinkContainDogsTestOn5Page() {
        String linkText = resultPage
                .goToPage(5)
                .getTextFromLinkNumber(1);
        assertTrue(linkText.contains("dogs"), "Text doesn't contain \"dogs\"");

    }

    @Test
    public void verify9ResultsLinksDisplayed() {
        assertTrue(resultPage.getNumberOfAllLinks() >= 9, "Links are less than 9");
    }

    @Test
    public void verifyFirstLinkContainCurrentSearchingWord() {
        String linkText = resultPage
                .clearInputField()
                .search("funny kitten")
                .getTextFromLinkNumber(1);

        assertTrue(linkText.contains("kitten"), "Text doesn't contain \"kitten\"");
        assertFalse(linkText.contains("dogs"), "Text contains \"dogs\"");
    }

    @Test
    public void verifyGoogleLogoDisplayed() {
        assertTrue($x(resultPage.getLogoXpath()).isDisplayed(), "Google logo isn't display");
    }

    @Test
    public void verifyNextLinkDisplayed() {
        $x(resultPage.getNextPageXpath()).shouldHave(Condition.visible);
    }

    @Test
    public void verifyPreviousLinkDisplayed() {
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue($x(resultPage.getNextPageXpath()).isDisplayed(), "Button next page isn't visible");

        resultPage.goToPage(4);
        softAssert.assertTrue($x(resultPage.getPreviousPageXpath()).isDisplayed(), "Button previous page isn't visible");
    }
}

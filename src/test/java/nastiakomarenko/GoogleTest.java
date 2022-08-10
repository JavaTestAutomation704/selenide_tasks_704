package nastiakomarenko;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.WebDriverRunner;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import static com.codeborne.selenide.Selenide.$x;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

public class GoogleTest extends BaseTestRunner {
    @Test
    public void verifyThatMethodSearchesDogs() {
        assertTrue(resultPage.getLinkNumberText(1).contains("dogs"), "Text doesn't contain \"dogs\"");
    }

    @Test
    public void verifyThatMethodSearchesTheNinthLink() {
        String ninthLink = $x("(//a)[9]").getAttribute("href");
        $x("(//a)[9]").click();

        String actualLink = WebDriverRunner.getWebDriver().getCurrentUrl();
        assertTrue(actualLink.contains(ninthLink));
    }

    @Test
    public void verifyThatGooglePageIsOpen() {
        resultPage.goToGoogleLogo();
        String currentUrl = WebDriverRunner.getWebDriver().getCurrentUrl();
        assertTrue(currentUrl.contains("https://www.google.com/"), "Google page didn't open");
    }

    @Test
    public void verifyThatFifthPageHasText() {
        String linkText = resultPage.goToPage(5).getLinkNumberText(1);
        assertTrue(linkText.contains("dogs"), "Text doesn't contain \"dogs\"");

    }

    @Test
    public void verifyThatNineLinksAreDisplayed() {
        assertTrue(resultPage.getNumberOfAllLinks() >= 9, "Number of links is less than 9");
    }

    @Test
    public void verifyThatMethodContainsText() {
        String currentText = resultPage.clearInputField().search("funny kitten").getLinkNumberText(1);

        assertTrue(currentText.contains("kitten"), "Text doesn't contain \"kitten\"");
        assertFalse(currentText.contains("dogs"), "Text contains \"dogs\"");
    }

    @Test
    public void verifyThatGoogleLogoIsDisplayed() {
        assertTrue($x(resultPage.logoXpath).isDisplayed(), "Google logo isn't display");
    }

    @Test
    public void verifyThatNextLinkIsDisplayed() {
        $x(resultPage.nextPageXpath).shouldHave(Condition.visible);
    }

    @Test
    public void verifyThatLinksAreDisplayed() {
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue($x(resultPage.nextPageXpath).isDisplayed(), "Button \"next page\" isn't visible");

        resultPage.goToPage(4);
        softAssert.assertTrue($x(resultPage.getPreviousPageXpath()).isDisplayed(), "Button \"previous page\" isn't visible");
    }
}
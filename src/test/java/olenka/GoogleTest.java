package olenka;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.WebDriverRunner;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;
import static org.testng.Assert.assertTrue;

public class GoogleTest {
    @BeforeClass
    public void beforeClass() {
        Configuration.browser = "chrome";
        Configuration.timeout = 10;
    }

    @BeforeMethod
    public void beforeMethod() {
        open("https://www.google.com/");
        $x("//input[@name='q']").setValue("funny dogs").pressEnter();
    }

    @Test
    public void verifyFirstLinkContainDogsTest () {
        $x("(//a/h3)[1]").shouldHave(text("dogs"));
    }

    @Test
    public void verifyValidNineLink() {
        String nineUrl = $x("(//a)[9]").getAttribute("href");
        $x("(//a)[9]").click();

        String newUrl = WebDriverRunner.getWebDriver().getCurrentUrl();
        assertTrue(newUrl.contains(nineUrl));
    }

    @Test
    public void verifyGoogleHomePageIsOpen() {
        $x("//div[@class = 'logo']").click();
        String currentUrl = WebDriverRunner.getWebDriver().getCurrentUrl();
        assertTrue(currentUrl.contains("https://www.google.com/"));
    }

    @Test
    public void verifyFirstLinkContainDogsTestOn5Page() {
        $x("//tr[@jsname = 'TeSSVd']//a[contains(@aria-label, '5')]").click();
        $x("(//a/h3)[1]").shouldHave(text("dogs"));
    }

    @Test
    public void  verify9ResultsLinksDisplayed() {
        for (int i = 1; i <= 9; i++) {
            $x(String.format("(//a/h3)[%s]",i)).isDisplayed();
        }
    }

    @Test
    public void verifyFirstLinkContainCurrentSearchingWord () {
        $x("//input[@name='q']").clear();
        $x("//input[@name='q']").setValue("funny kitten").pressEnter();

        $x("(//a/h3)[1]").shouldNotHave(text("dogs"));
        $x("(//a/h3)[1]").shouldHave(text("kitten"));
    }

    @Test
    public void verifyGoogleLogoDisplayed() {
        assertTrue($x("//div[@class = 'logo']").isDisplayed());
    }

    @Test
    public void verifyNextLinkDisplayed() {
        assertTrue($x("//td[@class='d6cvqb BBwThe']//a").isDisplayed());
    }

    @Test
    public void verifyPreviousLinkDisplayed() {
        assertTrue($x("//td[@class='d6cvqb BBwThe']//a").isDisplayed());
        $x("//tr[@jsname = 'TeSSVd']//a[contains(@aria-label, '4')]").click();
        assertTrue($x("//td[@class='d6cvqb BBwThe']//a").isDisplayed());
    }
}

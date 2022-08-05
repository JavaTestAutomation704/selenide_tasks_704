package ykireyeva;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.WebDriverRunner;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Selenide.$$x;
import static com.codeborne.selenide.Selenide.$x;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class GoogleTest {

    @BeforeClass
    public void ClassSetUp() {
        Configuration.browser = "chrome";
        Configuration.timeout = 10;
    }

    @BeforeMethod
    public void beforeMethod() {
        Selenide.open("https://www.google.com/");
    }

    @AfterClass
    public void tearDown() {
        Selenide.closeWebDriver();
    }

    @Test
    public void oneVerifyLinkText() {
        $x("//input[@aria-label='Пошук']").setValue("funny dogs").pressEnter();
        $x("(//a//h3)[1]").shouldHave(Condition.ownText("dogs"));
    }

    @Test
    public void twoVerifyHrefUrl() {
        $x("//input[@aria-label='Пошук']").setValue("funny dogs").pressEnter();
        String valueOfHref = $x("(//a[descendant::h3])[9]").getAttribute("href");
        $x("(//a[descendant::h3])[9]").click();
        String currentUrl = WebDriverRunner.getWebDriver().getCurrentUrl();
        assertEquals(valueOfHref, currentUrl);
    }

    @Test
    public void threeVerifyGoogleHomePageIsOpen() {
        $x("//input[@aria-label='Пошук']").setValue("funny dogs").pressEnter();
        $x("//div[@class='logo']").click();
        $x("//title").shouldHave(Condition.exactOwnText("Google"));
    }

    @Test
    public void fourVerifyLinkText() {
        $x("//input[@aria-label='Пошук']").setValue("funny dogs").pressEnter();
        $x("//a[@aria-label='Page 5']").click();
        $x("(//a//h3)[1]").shouldHave(Condition.ownText("dogs"));
    }

    @Test
    public void fiveVerifyNumberOfResultLinks() {
        $x("//input[@aria-label='Пошук']").setValue("funny dogs").pressEnter();
        $x("//a[@aria-label='Page 5']").click();
        int numberOfResultLinks = $$x("//a//h3").size();
        assertTrue(numberOfResultLinks >= 9);
    }

    @Test
    public void sixVerifyLinkText() {
        $x("//input[@aria-label='Пошук']").setValue("funny dogs").pressEnter();
        $x("//div[@aria-label='Очистити']").click();
        $x("//input[@aria-label='Пошук']").setValue("funny kitten").pressEnter();
        $x("(//a//h3)[1]")
                .shouldNotHave(Condition.ownText("dogs"))
                .shouldHave(Condition.ownText("kitten"));
    }

    @Test
    public void eightVerifyGoogleLogoIsDisplayed() {
        $x("//input[@aria-label='Пошук']").setValue("funny dogs").pressEnter();
        $x("//div[@class='logo']").shouldBe(Condition.enabled);
    }

    @Test
    public void nineVerifyNextBtnIsDisplayed() {
        $x("//input[@aria-label='Пошук']").setValue("funny dogs").pressEnter();
        $x("//span[text()='Уперед']").shouldBe(Condition.enabled);
    }

    @Test
    public void tenVerifyPreviousBtnIsDisplayed() {
        $x("//input[@aria-label='Пошук']").setValue("funny dogs").pressEnter();
        $x("//span[text()='Уперед']").shouldBe(Condition.enabled);
        $x("//a[@aria-label='Page 5']").click();
        $x("//span[text()='Назад']").shouldBe(Condition.enabled);
    }
}

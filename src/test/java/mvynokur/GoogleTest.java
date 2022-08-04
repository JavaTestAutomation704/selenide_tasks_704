package mvynokur;

import com.codeborne.selenide.CollectionCondition;
import jdk.jfr.Description;
import org.openqa.selenium.By;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

public class GoogleTest {

    @BeforeMethod
    public void setUp() {
        open("https://www.google.com");
        $(By.name("q")).val("funny dogs").pressEnter();
    }

    @Test
    @Description("Task 1")
    public void verify_firstLink_contains_dogs() {
        $x("//a/h3[@class = 'LC20lb MBeuO DKV0Md']").shouldHave(text("dogs"));
    }

    @Test
    @Description("Task 2")
    public void verify_nineLink_URL() {
        $x("//div[9]//h3[@class = 'LC20lb MBeuO DKV0Md']/parent::a")
                .shouldHave(attribute("href",
                        "https://www.youtube.com/watch?v=BldblUabS6Q&vl=en"));

    }

    @Test
    @Description("Task 3")
    public void verify_homePage_IsOpened() {
        $x("//div[@class = 'logo']").click();
        $x("//div[@class = 'k1zIA rSk4se']").should(be(visible));
    }

    @Test
    @Description("Task 4")
    public void verify_firstLink_on_fifthPage_contains_dogs() {
        $x("//tr[@jsname = 'TeSSVd']//a[contains(@aria-label, '5')]").click();
        $x("//a/h3[@class = 'LC20lb MBeuO DKV0Md']").shouldHave(text("dogs"));
    }

    @Test
    @Description("Task 5")
    public void verify_amountOf_displayed_resultsLinks() {
        $$x("//a/h3[@class = 'LC20lb MBeuO DKV0Md']").shouldHave(CollectionCondition.sizeGreaterThanOrEqual(9));
    }

    @Test
    @Description("Task 6/7")
    public void verify_firstLink_contains_funnyKitten() {
        $x("//input[@class = 'gLFyf gsfi']").clear();
        $x("//input[@class = 'gLFyf gsfi']").val("funny kitten").pressEnter();
        $x("//div[@class = 'Uroaid']").shouldHave(text("kitten")).shouldNotHave(text("dogs"));
    }

    @Test
    @Description("Task 8")
    public void verify_logoIsDisplayed() {
        $x("//div[@class = 'logo']").shouldHave(be(visible));
    }

    @Test
    @Description("Task 9")
    public void verify_nextLink_isDisplayed() {
        $x("//tr[@jsname = 'TeSSVd']//a/span[contains(text(), 'Next')]")
                .shouldHave(be(visible));
    }

    @Test
    @Description("Task 10")
    public void verify_previousLink_isDisplayed() {
        verify_nextLink_isDisplayed();
        $x("//tr[@jsname = 'TeSSVd']//a[contains(@aria-label, '4')]").click();
        $x("//tr[@jsname = 'TeSSVd']//a/span[contains(text(), 'Previous')]")
                .shouldHave(be(visible));
    }

}


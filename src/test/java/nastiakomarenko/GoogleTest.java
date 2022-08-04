package nastiakomarenko;


import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.WebDriverRunner;
import org.openqa.selenium.By;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;
import static org.testng.AssertJUnit.assertTrue;

public class GoogleTest {
    private final String FUNNY_DOGS = "funny dogs";

    @BeforeMethod
    public void setUp() {
        open("https://www.google.com");
        $(By.name("q")).val(FUNNY_DOGS).pressEnter();
    }

    @Test
    public void verify_search_dogs() {
        $x("//a/h3[@class = 'LC20lb MBeuO DKV0Md']").shouldHave(text("dogs"));
    }

    @Test
    public void verify_search_ninth_link(){
        String ninthLink = $x("(//a)[9]").getAttribute("href");
        $x("(//a)[9]").click();

        String actualLink = WebDriverRunner.getWebDriver().getCurrentUrl();
        assertTrue(actualLink.contains(ninthLink));

    }

    @Test
    public void verify_google_page_isOpen(){
        $x("//div[@class='logo']").click();
        $x("//img[@class='lnXdpd']").shouldBe(visible);
    }

    @Test
    public void verify_fifth_page(){
        $x("//tr[@jsname = 'TeSSVd']//a[contains(@aria-label, '5')]").click();
        $x("//a/h3[@class = 'LC20lb MBeuO DKV0Md']").shouldHave(text("dogs"));
    }

    @Test
    public void verify_nine_links_displayed(){
        $$x("//h3[@class = 'LC20lb MBeuO DKV0Md']").shouldHave(CollectionCondition.sizeGreaterThanOrEqual(9));
    }

    @Test
    public void verify_search_contains_kitten(){
        $x("//input[@class = 'gLFyf gsfi']").clear();
        $(By.name("q")).val("funny kitten").pressEnter();
        $x("//div[@class = 'mSA5Bd']").shouldHave(text("kitten")).shouldNotHave(text("dogs"));
    }

    @Test
    public void verify_google_logo_isDisplayed(){
        $x("//div[@class = 'logo']").shouldBe(visible);
    }

    @Test
    public void verify_nextLink_isDisplayed(){
        $x("//a[@id='pnnext']/span[@class]").shouldBe(visible);
    }

    @Test
    public void verify_links_areDisplayed(){
        $x("//a[@id='pnnext']/span[@class]").shouldBe(visible);
        $x("//tr[@jsname = 'TeSSVd']//a[contains(@aria-label, '4')]").click();
        $x("//span[@class='SJajHc NVbCr']").shouldBe(visible);
    }

}



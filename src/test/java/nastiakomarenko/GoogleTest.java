package nastiakomarenko;


import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.WebDriverRunner;
import org.openqa.selenium.By;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import javax.naming.directory.SearchResult;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;
import static org.testng.AssertJUnit.assertTrue;

public class GoogleTest extends HomePage{


    @Test
    public void verifyThatMethodSearchesDogs() {
        $x("//a/h3[@class = 'LC20lb MBeuO DKV0Md']").shouldHave(text("dogs"));
    }

    @Test
    public void verifyThatMethodSearchesTheNinthLink(){
        String ninthLink = $x("(//a)[9]").getAttribute("href");
        $x("(//a)[9]").click();

        String actualLink = WebDriverRunner.getWebDriver().getCurrentUrl();
        assertTrue(actualLink.contains(ninthLink));

    }

    @Test
    public void verifyThatGooglePageIsOpen(){
        $x("//div[@class='logo']").click();
        $x("//img[@class='lnXdpd']").shouldBe(visible);
    }

    @Test
    public void verifyThatFifthPageHasText(){
        $x("//tr[@jsname = 'TeSSVd']//a[contains(@aria-label, '5')]").click();
        $x("//a/h3[@class = 'LC20lb MBeuO DKV0Md']").shouldHave(text("dogs"));
    }

    @Test
    public void verifyThatNineLinksAreDisplayed(){
        $$x("//h3[@class = 'LC20lb MBeuO DKV0Md']").shouldHave(CollectionCondition.sizeGreaterThanOrEqual(9));
    }

    @Test
    public void verifyThatMethodContainsText(){
        $x("//input[@class = 'gLFyf gsfi']").clear();
        $(By.name("q")).val("funny kitten").pressEnter();
        $x("//div[@class = 'mSA5Bd']").shouldHave(text("kitten")).shouldNotHave(text("dogs"));
    }

    @Test
    public void verifyThatGoogleLogoIsDisplayed(){
        $x("//div[@class = 'logo']").shouldBe(visible);
    }

    @Test
    public void verifyThatNextLinkIsDisplayed(){
        $x("//a[@id='pnnext']/span[@class]").shouldBe(visible);
    }

    @Test
    public void verifyThatLinksAreDisplayed(){
        $x("//a[@id='pnnext']/span[@class]").shouldBe(visible);
        $x("//tr[@jsname = 'TeSSVd']//a[contains(@aria-label, '4')]").click();
        $x("//span[@class='SJajHc NVbCr']").shouldBe(visible);
    }
}
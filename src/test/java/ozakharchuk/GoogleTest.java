package ozakharchuk;

import com.codeborne.selenide.*;
import com.codeborne.selenide.CollectionCondition;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import static com.codeborne.selenide.WebDriverConditions.*;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

public class GoogleTest {

    @BeforeClass
    public void downloadBrowser(){
        Configuration.browser = "chrome";
    }

    @BeforeMethod
    public void openGoogleAndTypeSearchItem() {
        Selenide.open("https://www.google.com/");
        $x("//input[@name='q']").setValue("funny dogs").pressEnter();
    }

    @Test
    public void verifyFirstLinkNameContainsDogs() {
        $x("//span[@id='fld_1']/following-sibling::div[1]//a/h3").shouldHave(text("dogs"));
    }

    @Test
    public void verifyNinthLinkValid() {
        $x("//span[@id='fld_1']/following-sibling::div[9]//a[1]").shouldHave(attribute("href"));
    }

    @Test
    public void verifyGoogleHomePageIsOpen() {
        $x("//div[@class='logo']/a").click();
        webdriver().shouldHave(urlStartingWith("https://www.google.com/"));
    }

    @Test
    public void verifyFirstLinkNameContainsDogsFifthPage() {
        $x("//a[@aria-label='Page 5']").click();
        $x("//div[@id='rso']/div//h3[1]").shouldHave(text("dog"));
    }

    @Test
    public void verifyAtLeastNineLinks() {
        $$x("//div[@id='rso']/div//a/h3").shouldHave(CollectionCondition.sizeGreaterThanOrEqual(9));
    }

    @Test
    public void verifyFirstLinkContainsKitten() {
        $x("//input[@name='q']").clear();
        $x("//input[@name='q']").setValue("funny kitten").pressEnter();
        $x("//div[@class='Uroaid'][1]").shouldNotHave(text("dogs")).shouldHave(text("kitten"));
    }

    @Test
    public void verifyGoogleLogoIsDisplayed() {
        $x("//img[@alt='Google']").shouldBe(visible);
    }

    @Test
    public void verifyNextLinkIsDisplayed() {
        $x("//table[@role='presentation']//a[@id='pnnext']").shouldBe(visible);
    }

    @Test
    public void verifyNextAndPreviousLinkIsDisplayed() {
        $x("//table[@role='presentation']//a[@id='pnnext']").shouldBe(visible);
        $x("//a[@aria-label='Page 4']").click();
        $x("//table[@role='presentation']//a[@id='pnprev']").shouldBe(visible);
    }
}

package YaroslavTarasovych;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Selenide;
import org.openqa.selenium.Keys;
import org.testng.Assert;
import org.testng.annotations.*;

import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Condition.*;


public class GoogleTest {

    String searchTerm = "funny dogs";
    String inputTitle = "//input[@title]";

    @BeforeClass
    public void beforeClass(){
        Configuration.browser = "chrome";
        Configuration.timeout = 20;
    }

    @BeforeMethod
    public void beforeMethod(){

        Selenide.open("https://www.google.com/");
    }

    @AfterClass
    public void afterClass(){
        Selenide.closeWebDriver();
    }

    @Test
    public void verifySearchFirstLink(){
        $x(inputTitle).setValue(searchTerm)
                .sendKeys(Keys.ENTER);
        $x("//a[@class]//h3")
                .shouldHave(text("dogs"));
    }

    @Test
    public void verifySearchNinthLink(){
        $x(inputTitle)
                .setValue(searchTerm)
                .sendKeys(Keys.ENTER);
        String hrefLink = $x("//div[@id='rso']/div[9]//div[1]/a").getAttribute("href");
        Assert.assertTrue(hrefLink.contains("www.youtube.com/watch?"));
    }

    @Test
    public void verifyGooglePageIsOpen(){
        $x(inputTitle).setValue(searchTerm)
                .sendKeys(Keys.ENTER);
        $x("//img[@alt='Google']")
                .click();
        String href = $x("//html")
                .getAttribute("itemtype");
        Assert.assertTrue(href.contains("WebPage"));
    }

    @Test
    public void verifySearchFirstLinkInFifthPage(){
        $x(inputTitle)
                .setValue(searchTerm)
                .sendKeys(Keys.ENTER);
        $x("//a[@aria-label='Page 5']")
                .click();
        $x("//div[@id='rso']/div[1]/div/div[1]/div//h3")
                .shouldHave(text("dogs"));
    }

    @Test
    public void verifySearchNineLinksAreDisplayed(){
        $x(inputTitle)
                .setValue(searchTerm)
                .sendKeys(Keys.ENTER);
        ElementsCollection list = $$x("//div[@id='rso']/div");
        Assert.assertTrue(list.size() >= 9);
    }

    @Test
    public void verifySearchFirstLinkAnotherInput(){
        $x(inputTitle)
                .setValue(searchTerm)
                .clear();
        $x(inputTitle)
                .setValue("funny kitten")
                .sendKeys(Keys.ENTER);
        $x("//a[@class]//h3")
                .shouldNot(text("dogs"));
    }

    @Test
    public void verifyLogoIsDisplayed(){
        $x(inputTitle).setValue(searchTerm)
                .sendKeys(Keys.ENTER);
        $x("//img[@alt='Google']")
                .shouldBe(visible);
    }

    @Test
    public void verifyBtnNextPageIsDisplayed(){
        $x(inputTitle)
                .setValue(searchTerm)
                .sendKeys(Keys.ENTER);
        $x("//a[@id='pnnext']/span[@class]")
                .shouldBe(visible);
    }

    @Test
    public void verifyBtnNextPageAndPreviousAreDisplayed(){
        $x(inputTitle)
                .setValue(searchTerm)
                .sendKeys(Keys.ENTER);
        boolean nextPageIsDisplayed = $x("//a[@id='pnnext']/span[@class]").isDisplayed();
        $x("//a[@aria-label='Page 4']")
                .click();
        boolean previousPageIsDisplayed = $x("//a[@id='pnprev']/span[@class]").isDisplayed();

        Assert.assertTrue(nextPageIsDisplayed);
        Assert.assertTrue(previousPageIsDisplayed);
    }

}

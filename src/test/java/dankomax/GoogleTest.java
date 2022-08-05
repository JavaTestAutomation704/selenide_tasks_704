package dankomax;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.CollectionCondition.*;
import static com.codeborne.selenide.Selenide.*;
import com.codeborne.selenide.SelenideElement;


public class GoogleTest {
    private final String searchField = "//input[@name='q']";
    private final String searchResultContainer = "div[contains(@class, 'g ')]";

    @BeforeMethod
    public void searchFunnyDogs() {
        open("https://www.google.com");
        $x(searchField).setValue("funny dogs").pressEnter();
    }

    @Test
    public void verifyFirstResultTitle() {
        searchResultTitleByNumber(1).shouldHave(text("dogs"));
    }

    @Test
    public void verifyNinthResultLinkHrefHasValidURL() {
        searchResultTitleByNumber(9)
                .ancestor("a")
                .shouldHave(attributeMatching("href", "^((ftp|http|https)://)?www\\..*"));
    }

    @Test
    public void verifyHomePageLinkWorks() {
        $x("//a[@id='logo']").click();
        Assert.assertEquals(title(), "Google");
    }

    @Test
    public void verifyFirstResultTitleOnFifthPage() {
        openPage(5);
        searchResultTitleByNumber(1)
                .ancestor(searchResultContainer)
                .shouldHave(text("dog"));
    }

    @Test
    public void verifySearchResultsQuantityIsMoreThenNine() {
        $$x("//a/h3/ancestor::" + searchResultContainer).shouldBe(sizeGreaterThanOrEqual(9));
    }

    @Test
    public void verifyRepeatSearchFirstResultTitle() {
        $x(searchField).clear();
        $x(searchField).setValue("funny kitten").pressEnter();
        searchResultTitleByNumber(1)
                .ancestor(searchResultContainer)
                .shouldNotHave(text("dogs")).shouldHave(text("kitten"));
    }

    @Test
    public void verifyGoogleLogoIsDisplayed() {
        $x("//a[@id='logo']").shouldBe(visible);
    }

    @Test
    public void verifyNextLinkIsDisplayed() {
        $x("//a[@id='pnnext']").shouldBe(visible);
    }

    @Test
    public void verifyPreviousLinkIsDisplayed() {
        $x("//a[@id='pnnext']").shouldBe(visible);
        openPage(4);
        $x("//a[@id='pnprev']").shouldBe(visible);
    }

    private void openPage(int page) {
        $x("//div[@role='navigation']//a[contains(@aria-label, '" + page + "')]").click();
    }

    private SelenideElement searchResultTitleByNumber(int titleNumber) {
        return $x(String.format("(//%s//a//h3)[%d]", searchResultContainer, titleNumber));
    }
}

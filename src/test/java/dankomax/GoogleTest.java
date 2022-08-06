package dankomax;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.CollectionCondition.*;


public class GoogleTest extends BaseTestRunner {
    private final HomePage homePage = new HomePage();
    private final SearchPage searchPage = new SearchPage();

    @BeforeMethod
    public void searchFunnyDogs() {
        homePage.searchPhrase("Funny dogs");
    }

    @Test
    public void verifyFirstResultTitle() {
        searchPage.searchResultTitleByNumber(1).shouldHave(text("dogs"));
    }

    @Test
    public void verifyNinthResultLinkHrefHasValidURL() {
        searchPage.searchResultLinkByNumber(9)
                .shouldHave(attributeMatching("href", "^((ftp|http|https)://)?www\\..*"));
    }

    @Test
    public void verifyHomePageLinkWorks() {
        searchPage.openHomePage()
                .title().shouldHave(attribute("text", "Google"));
        homePage.languageSelection().shouldBe(visible);
        homePage.feelingLuckyButton().shouldBe(visible).shouldHave(value("I'm Feeling Lucky"));
        homePage.settingsButton().shouldBe(visible).shouldHave(text("Settings"));
    }

    @Test
    public void verifyFirstResultTitleOnFifthPage() {
        searchPage.paginationOpenPage(5)
                .searchResultByNumber(1).shouldHave(text("dog"));
    }

    @Test
    public void verifySearchResultsQuantityIsSufficient() {
        searchPage.searchResultCollection().shouldBe(sizeGreaterThanOrEqual(9));
    }

    @Test
    public void verifyRepeatSearchFirstResultTitle() {
        homePage.searchPhrase("funny kitten")
                .searchResultByNumber(1).shouldNotHave(text("dogs")).shouldHave(text("kitten"));
    }

    @Test
    public void verifyGoogleLogoIsDisplayed() {
        searchPage.googleLogo().shouldBe(visible);
    }

    @Test
    public void verifyNextLinkIsDisplayed() {
        searchPage.paginationNextButton().shouldBe(visible);
    }

    @Test
    public void verifyPreviousLinkIsDisplayed() {
        searchPage.paginationNextButton().shouldBe(visible);
        searchPage.paginationOpenPage(4);
        searchPage.paginationPreviousButton().shouldBe(visible);
    }
}

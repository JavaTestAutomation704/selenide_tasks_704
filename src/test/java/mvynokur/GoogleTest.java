package mvynokur;

import com.codeborne.selenide.CollectionCondition;
import jdk.jfr.Description;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Condition.*;

public class GoogleTest extends BaseTestRunner {

    private final String searchText = "funny dogs";

    @Test
    @Description("Task 1")
    public void verifyThatSearchFunctionalityWorks() {
        new GooglePage().searchFor(searchText);
        new SearchResultsPage().getLinkNameByNumber(1).shouldHave(text("dogs"));
    }

    @Test
    @Description("Task 2")
    public void verifyNineLinkURLCorrectness() {
        new GooglePage().searchFor(searchText);
        new SearchResultsPage().getLinkNameByNumber(9).
                shouldHave(attributeMatching("href", "((http|https)://)(www.)?"
                        + "[a-zA-Z0-9@:%._\\+~#?&//=]{2,256}\\.[a-z]"
                        + "{2,6}\\b([-a-zA-Z0-9@:%._\\+~#?&//=]*)"));

    }

    @Test
    @Description("Task 3")
    public void verifyNavigationToHomePage() throws InterruptedException {
        new GooglePage().searchFor(searchText)
                .navigateToHomePage().feelingLuckyButtonIsVisible().languageBlockIsVisible();
    }

    @Test
    @Description("Task 4")
    public void verifySearchFunctionalityOnDifferentPages() {
        new GooglePage().searchFor(searchText).navigateToPage(5);
        new SearchResultsPage().getLinkNameByNumber(1).shouldHave(text("dogs"));
    }

    @Test
    @Description("Task 5")
    public void verifySearchResultsLinksAmount() {
        new GooglePage().searchFor(searchText);
        new SearchResultsPage().getLinkNames().shouldHave(CollectionCondition.sizeGreaterThanOrEqual(9));
    }

    @Test
    @Description("Task 6/7")
    public void verifySearchFunctionalityWithDifferentInputs() {
        new GooglePage().searchFor(searchText).clearSearchField().searchFor("funny kitten");
        new SearchResultsPage().getDescriptions().get(0)
                .shouldHave(text("kitten")).shouldNotHave(text("dogs"));
    }

    @Test
    @Description("Task 8")
    public void verifyLogoIconIsDisplayed() {
        new GooglePage().searchFor(searchText).logoIconIsVisible();
    }

    @Test
    @Description("Task 9")
    public void verifyThatNextPageLinkIsDisplayed() {
        new GooglePage().searchFor(searchText).nextPageIsVisible();
    }

    @Test
    @Description("Task 10")
    public void verifyThatPreviousPageLinkIsDisplayed() {
        verifyThatNextPageLinkIsDisplayed();
        new GooglePage().navigateToPage(4).previousPageIsVisible();
    }
}

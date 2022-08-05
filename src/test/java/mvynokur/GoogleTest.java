package mvynokur;

import com.codeborne.selenide.CollectionCondition;
import jdk.jfr.Description;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Condition.visible;

public class GoogleTest extends GooglePageBaseMethods {

    @Test
    @Description("Task 1")
    public void verifyThatSearchFunctionalityWorks() {
        SearchResultsPage results = new SearchResultsPage();
        results.searchResultLinksNames().get(0).shouldHave(text("dogs"));
    }

    @Test
    @Description("Task 2")
    public void verifyNineLinkURLCorrectness() {
        SearchResultsPage results = new SearchResultsPage();
        results.searchResultLink(9).
                shouldHave(attributeMatching("href", "((http|https)://)(www.)?"
                        + "[a-zA-Z0-9@:%._\\+~#?&//=]{2,256}\\.[a-z]"
                        + "{2,6}\\b([-a-zA-Z0-9@:%._\\+~#?&//=]*)"));

    }

    @Test
    @Description("Task 3")
    public void verifyNavigationToHomePage() throws InterruptedException {
        GooglePage googlePage = new GooglePage();
        googlePage.navigateToHomePage();
        googlePage.getUkrainianLanguageLink().shouldBe(visible);
    }

    @Test
    @Description("Task 4")
    public void verifySearchFunctionalityOnDifferentPages() {
        GooglePage googlePage = new GooglePage();
        googlePage.navigateToPage(5);
        SearchResultsPage results = new SearchResultsPage();
        results.searchResultLinksNames().get(0).shouldHave(text("dogs"));
    }

    @Test
    @Description("Task 5")
    public void verifySearchResultsLinksAmount() {
        SearchResultsPage results = new SearchResultsPage();
        results.searchResultLinksNames().shouldHave(CollectionCondition.sizeGreaterThanOrEqual(9));
    }

    @Test
    @Description("Task 6/7")
    public void verifySearchFunctionalityWithDifferentInputs() {
        GooglePage googlePage = new GooglePage();
        googlePage.clearSearchField();
        searchFor("funny kitten");
        SearchResultsPage results = new SearchResultsPage();
        results.searchResultsDescriptions().get(0)
                .shouldHave(text("kitten")).shouldNotHave(text("dogs"));
    }

    @Test
    @Description("Task 8")
    public void verifyLogoIconIsDisplayed() {
        GooglePage googlePage = new GooglePage();
        googlePage.getLogoIcon().shouldBe(visible);
    }

    @Test
    @Description("Task 9")
    public void verifyThatNextPageLinkIsDisplayed() {
        GooglePage googlePage = new GooglePage();
        googlePage.getNextPage().shouldBe(visible);
    }

    @Test
    @Description("Task 10")
    public void verifyThatPreviousPageLinkIsDisplayed() {
        verifyThatNextPageLinkIsDisplayed();
        GooglePage googlePage = new GooglePage();
        googlePage.navigateToPage(4);
        googlePage.getPreviousPage().shouldBe(visible);
    }
}

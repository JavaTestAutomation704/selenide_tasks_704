package dankomax;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;


public class GoogleTest extends BaseTestRunner {
    private final HomePage homePage = new HomePage();
    private final SearchPage searchPage = new SearchPage();

    @BeforeMethod
    public void searchFunnyDogs() {
        homePage.searchPhrase("Funny dogs");
    }

    @Test
    public void verifyFirstResultTitle() {
        searchPage.verifySearchResultTitleHasExpectedText(1, "dogs");
    }

    @Test
    public void verifyNinthResultLinkHrefHasValidURL() {
        searchPage.verifyValidityOfSearchResultLinkByNumber(9);
    }

    @Test
    public void verifyHomePageLinkWorks() {
        searchPage.openHomePage().verifyCurrentPageIsHomePage();
    }

    @Test
    public void verifyFirstResultTitleOnFifthPage() {
        searchPage.paginationOpenPage(5)
                .verifySearchResultTitleHasExpectedText(1, "dog");
    }

    @Test
    public void verifySearchResultsQuantityIsSufficient() {
        searchPage.verifySearchResultsQuantityIsGreaterOrEqual(9);
    }

    @Test
    public void verifyRepeatSearchFirstResultTitle() {
        homePage.searchPhrase("funny kitten")
                .verifySearchResultByNumberExcludeText(1, "dogs")
                .verifySearchResultByNumberIncludeText(1, "kitten");
    }

    @Test
    public void verifyGoogleLogoIsDisplayed() {
        searchPage.checkGoogleLogoIsVisible();
    }

    @Test
    public void verifyNextLinkIsDisplayed() {
        searchPage.checkPaginationNextButtonIsVisible();
    }

    @Test
    public void verifyPreviousLinkIsDisplayed() {
        searchPage.checkPaginationNextButtonIsVisible();
        searchPage.paginationOpenPage(4);
        searchPage.checkPaginationPreviousButtonIsVisible();
    }
}

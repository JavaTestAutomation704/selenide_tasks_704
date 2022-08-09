package mvynokur;

import org.testng.annotations.BeforeMethod;

public class BaseTestRunner {

    protected GooglePage homePage;
    protected SearchResultsPage searchResults;
    private final String baseSearch = "funny dogs";

    @BeforeMethod
    public void openHomePageAndSearch() {
        homePage = new GooglePage().openHomePage().searchFor(baseSearch);
        searchResults = new SearchResultsPage();
    }
}

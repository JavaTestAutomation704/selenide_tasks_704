package deprecated.mvynokur;

import org.testng.annotations.BeforeMethod;

public class BaseTestRunner {
    protected GooglePage homePage = new GooglePage();
    protected SearchResultsPage searchResultsPage;

    @BeforeMethod
    public void openHomePageAndSearch() {
        searchResultsPage = homePage.open().searchFor("funny dogs");
    }
}
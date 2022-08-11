package mvynokur;

import org.testng.annotations.BeforeMethod;

public class BaseTestRunner {

    protected GooglePage homePage = new GooglePage();
    protected SearchResultsPage searchResultsPage;

    @BeforeMethod
    public void openHomePageAndSearch() {
        homePage.open();
        searchResultsPage = homePage.searchFor("funny dogs");
    }
}

package mvynokur;

import org.testng.annotations.BeforeMethod;

public class BaseTestRunner {

    protected GooglePage homePage = new GooglePage();
    protected SearchResultsPage searchResultsPage = new SearchResultsPage();

    @BeforeMethod
    public void openHomePageAndSearch() {
        homePage.open().searchFor("funny dogs");
    }
}

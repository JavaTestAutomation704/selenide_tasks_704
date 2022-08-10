package mvynokur;

import org.testng.annotations.BeforeMethod;

public class BaseTestRunner {

    protected GooglePage homePage;
    protected SearchResultsPage searchResultsPage;

    @BeforeMethod
    public void openHomePageAndSearch() {
        homePage = new GooglePage().openHomePage();
        searchResultsPage = homePage.searchFor("funny dogs");
    }
}

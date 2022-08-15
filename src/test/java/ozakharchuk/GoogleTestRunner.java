package ozakharchuk;

import org.testng.annotations.BeforeMethod;

public class GoogleTestRunner {

    protected GooglePage homePage;
    protected SearchResultPage searchResultsPage;

    @BeforeMethod
    public void openHomePageAndSearch() {
        homePage = new GooglePage().open();
        searchResultsPage = homePage.searchFor("funny dogs");
    }
}

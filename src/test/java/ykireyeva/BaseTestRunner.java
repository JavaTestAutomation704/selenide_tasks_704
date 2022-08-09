package ykireyeva;

import com.codeborne.selenide.Configuration;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

public class BaseTestRunner {
    protected GoogleSearchPage searchPage;
    protected GoogleSearchResultsPage resultsPage;

    @BeforeClass
    public void setUpBrowserBeforeClass() {
        Configuration.browser = "chrome";
        Configuration.timeout = 10;
    }

    @BeforeMethod
    public void openGoogleSearchPageBeforeMethod() {
        searchPage = new GoogleSearchPage().openGoogleSearchPage();
        resultsPage = new GoogleSearchResultsPage();
        baseSearch();
    }

    public void baseSearch() {
        searchPage.search("funny dogs");
    }
}

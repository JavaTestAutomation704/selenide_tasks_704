package ykireyeva;

import com.codeborne.selenide.Configuration;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

public class BaseTestRunner {
    protected GooglePage searchPage;
    protected GoogleSearchResultsPage searchResultsPage;

    @BeforeClass
    public void setUpBrowserBeforeClass() {
        Configuration.browser = "chrome";
        Configuration.timeout = 10;
    }

    @BeforeMethod
    public void baseSearch() {
        searchPage = new GooglePage().openSearchPage();
        searchResultsPage = searchPage.search("funny dogs");
    }
}

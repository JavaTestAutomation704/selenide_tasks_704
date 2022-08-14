package ykireyeva;

import com.codeborne.selenide.Configuration;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

public class BaseTestRunner {
    protected GooglePage homePage = new GooglePage();
    protected SearchResultsPage searchResultsPage;

    @BeforeClass
    public void setUpBrowserBeforeClass() {
        Configuration.browser = "chrome";
        Configuration.timeout = 10;
    }

    @BeforeMethod
    public void openSearchResultsPage() {
        searchResultsPage = homePage.open().search("funny dogs");
    }
}

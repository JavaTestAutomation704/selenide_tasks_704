package deprecated.nastiakomarenko;

import com.codeborne.selenide.Configuration;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

public class BaseTestRunner {
    protected GooglePage googlePage = new GooglePage();
    protected GoogleSearchResultsPage searchResultsPage;

    @BeforeClass
    public void setUpBrowserBeforeClass() {
        Configuration.browser = "chrome";
        Configuration.timeout = 10;
    }

    @BeforeMethod
    public void openSearchResultsPage() {
        searchResultsPage = googlePage.open().search("funny dogs");
    }
}
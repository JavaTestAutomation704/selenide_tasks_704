package nastiakomarenko;

import com.codeborne.selenide.Configuration;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

public class BaseTestRunner {
    protected GooglePage googlePage = new GooglePage();
    protected GoogleSearchResultsPage searchResultsPage = new GoogleSearchResultsPage();

    @BeforeClass
    public void setUpBrowserBeforeClass() {
        Configuration.browser = "chrome";
        Configuration.timeout = 10;
    }

    @BeforeMethod
    public void openSearchResultsPage() {
        googlePage.open().search("funny dogs");
    }
}
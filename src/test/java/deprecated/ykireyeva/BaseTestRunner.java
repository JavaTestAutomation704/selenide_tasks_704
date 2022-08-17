package deprecated.ykireyeva;

import com.codeborne.selenide.Configuration;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

public class BaseTestRunner {
    protected GooglePage homePage = new GooglePage();
    protected SearchResultsPage searchResultsPage;

    @BeforeClass
    public void setUpBrowserBeforeClass() {
        Configuration.browser = "chrome";
        Configuration.browserCapabilities = new ChromeOptions()
                .addArguments("--lang=en-GB");
        Configuration.timeout = 10;
    }

    @BeforeMethod
    public void openSearchResultsPage() {
        searchResultsPage = homePage.open().search("funny dogs");
    }
}

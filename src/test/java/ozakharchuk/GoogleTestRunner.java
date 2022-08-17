package ozakharchuk;

import com.codeborne.selenide.Configuration;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

public class GoogleTestRunner {

    protected GooglePage homePage;
    protected SearchResultPage searchResultsPage;

    @BeforeClass
    public void setUpBrowser() {
        Configuration.browser = "chrome";
        Configuration.browserCapabilities = new ChromeOptions()
                .addArguments("--lang=en-GB");
        Configuration.timeout = 10000;
    }

    @BeforeMethod
    public void openHomePageAndSearch() {
        homePage = new GooglePage().open();
        searchResultsPage = homePage.searchFor("funny dogs");
    }
}

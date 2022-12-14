package deprecated.dankomax;

import com.codeborne.selenide.Configuration;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;


public class TestRunner {
    protected HomePage homePage;
    protected SearchResultsPage searchResultsPage;

    @BeforeClass
    public void setUp() {
        Configuration.browser = "chrome";
        Configuration.timeout = 10;
        Configuration.browserSize = "1920x1080";
        Configuration.browserCapabilities = new ChromeOptions()
                .addArguments("--lang=en-GB");
    }

    @BeforeMethod
    public void performSearch() {
        homePage = new HomePage().open();
        searchResultsPage = homePage.search("funny dogs");
    }
}
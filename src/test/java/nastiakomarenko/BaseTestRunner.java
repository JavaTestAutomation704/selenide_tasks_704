package nastiakomarenko;

import com.codeborne.selenide.Configuration;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

import static com.codeborne.selenide.Selenide.open;

public class BaseTestRunner {
    protected GooglePage basePage;
    protected GoogleSearchResultsPage resultPage;

    @BeforeClass
    public void initializeBrowser() {
        Configuration.browser = "chrome";
        Configuration.timeout = 10;
    }

    @BeforeMethod
    public void openGooglePage() {
        basePage = new GooglePage().open();
        resultPage = basePage.search("funny dogs");
    }
}
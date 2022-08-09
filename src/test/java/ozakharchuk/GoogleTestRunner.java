package ozakharchuk;

import com.codeborne.selenide.Configuration;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

public class GoogleTestRunner {

    protected GoogleSearchResultPage googleSearchResultsPage;

    @BeforeClass
    public void downloadBrowser() {
        Configuration.browser = "chrome";
    }

    @BeforeMethod
    public void openGoogleAndTypeSearchItem() {
        googleSearchResultsPage = new GoogleHomePage()
                .openGoogleHomePage()
                .searchForText("funny dogs");
    }
}

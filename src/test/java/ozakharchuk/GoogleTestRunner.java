package ozakharchuk;

import com.codeborne.selenide.Configuration;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

public class GoogleTestRunner {

    protected GooglePage googlePage;
    protected GoogleSearchResultPage googleSearchResultsPage;

    @BeforeClass
    public void downloadBrowser() {
        Configuration.browser = "chrome";
    }

    @BeforeMethod
    public void openGooglePageAndSearch() {
        googlePage = new GooglePage().openGoogleHomePage();
        googleSearchResultsPage = googlePage.searchForText("funny dogs");
    }
}

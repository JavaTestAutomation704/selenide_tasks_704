package deprecated.odashynych;

import com.codeborne.selenide.Configuration;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;


public class TestRunner {
    protected GooglePage googlePage = new GooglePage();
    protected GoogleResultPage resultPage;

    @BeforeClass
    public void initializeBrowser() {
        Configuration.browser = "chrome";
        Configuration.timeout = 10;
    }

    @BeforeMethod
    public void openHomePageAndSearch() {
        googlePage.open();
        resultPage = googlePage.search("funny dogs");
    }
}

package odashynych;

import com.codeborne.selenide.Configuration;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;


public class TestRunner {
    protected GooglePage googlePage;
    protected GoogleResultPage resultPage;

    @BeforeClass
    public void initializeBrowser() {
        Configuration.browser = "chrome";
        Configuration.timeout = 10;
    }

    @BeforeMethod
    public void openHomePageAndSearch() {
        googlePage = new GooglePage().openHomePage();
        resultPage = googlePage.search("funny dogs");
    }

}

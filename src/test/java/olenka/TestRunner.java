package olenka;

import com.codeborne.selenide.Configuration;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

import static com.codeborne.selenide.Selenide.open;

public class TestRunner {
    protected GoogleBasePage basePage;
    protected GoogleResultPage resultPage;

    @BeforeClass
    public void initializeBrowser() {
        Configuration.browser = "chrome";
        Configuration.timeout = 10;
    }

    @BeforeMethod
    public void openGooglePage() {
        open("https://www.google.com/");
        basePage = new GoogleBasePage();
        resultPage = basePage.search("funny dogs");
    }

}

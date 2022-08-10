package ytarasovych;

import com.codeborne.selenide.Configuration;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

public class BaseMethods {
    protected GooglePage googleHomePage;

    @BeforeClass
    public void setConfigurations() {
        Configuration.browser = "chrome";
        Configuration.timeout = 20;
    }

    @BeforeMethod
    public void openHomePage() {
        googleHomePage = new GooglePage().openHomePage();
    }
}
package mvynokur;

import org.testng.annotations.BeforeMethod;

import static com.codeborne.selenide.Selenide.open;

public class BaseTestRunner {

    @BeforeMethod
    public void openHomePage() {
        open("https://www.google.com");
    }
}

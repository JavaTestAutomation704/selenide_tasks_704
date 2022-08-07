package nastiakomarenko;

import org.openqa.selenium.By;
import org.testng.annotations.BeforeMethod;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class HomePage {
    private static final String searchTerm = "funny dogs";

    @BeforeMethod
    public void openHomePageAndSearch() {
        open("https://www.google.com");
        $(By.name("q")).val(searchTerm).pressEnter();

    }
}

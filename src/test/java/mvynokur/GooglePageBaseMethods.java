package mvynokur;

import org.openqa.selenium.By;
import org.testng.annotations.BeforeMethod;
import static com.codeborne.selenide.Selenide.*;

public class GooglePageBaseMethods {

    private final String searchText = "funny dogs";

    @BeforeMethod
    public void openHomePage() {
        open("https://www.google.com");
        searchFor(searchText);
    }

    public void searchFor(String text) {
        $(By.name("q")).val(text).pressEnter();
    }
}

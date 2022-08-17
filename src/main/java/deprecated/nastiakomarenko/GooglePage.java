package deprecated.nastiakomarenko;

import com.codeborne.selenide.Selenide;

import static com.codeborne.selenide.Selenide.$x;

public class GooglePage {
    public GoogleSearchResultsPage search(String text) {
        $x("//input[@name='q']").setValue(text).pressEnter();
        return new GoogleSearchResultsPage();
    }

    public GooglePage open() {
        Selenide.open("https://www.google.com/");
        return this;
    }
}
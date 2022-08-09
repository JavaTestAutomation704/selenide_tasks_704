package odashynych;

import com.codeborne.selenide.Selenide;

import static com.codeborne.selenide.Selenide.$x;

public class GoogleBasePage {
    public GoogleResultPage search(String text) {
        $x("//input[@name='q']").setValue(text).pressEnter();
        return new GoogleResultPage();
    }

    public GoogleBasePage open() {
        Selenide.open("https://www.google.com/");
        return this;
    }
}

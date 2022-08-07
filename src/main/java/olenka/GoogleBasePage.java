package olenka;

import static com.codeborne.selenide.Selenide.$x;

public class GoogleBasePage {
    protected final String inputField = "//input[@name='q']";

    public GoogleResultPage search(String text) {
        $x(inputField).setValue(text).pressEnter();

        return new GoogleResultPage();
    }

    public GoogleBasePage clearInputField(){
        $x(inputField).clear();
        return this;
    }
}

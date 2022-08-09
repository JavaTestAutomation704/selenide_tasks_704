package ozakharchuk;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.ex.ElementNotFound;

import static com.codeborne.selenide.Selenide.$x;

public class GoogleHomePage {

    public GoogleSearchResultPage searchForText(String text) {
        $x("//input[@name='q']").clear();
        $x("//input[@name='q']").setValue(text).pressEnter();
        return new GoogleSearchResultPage();
    }

    public GoogleHomePage openGoogleHomePage() {
        Selenide.open("https://www.google.com");
        return this;
    }

    public boolean isLanguageBlockVisible() {
        try {
            return $x("//div[@id='SIvCob']").isDisplayed();
        } catch (ElementNotFound e) {
            return false;
        }
    }
}

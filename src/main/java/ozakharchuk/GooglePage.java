package ozakharchuk;

import com.codeborne.selenide.Selenide;
import ozakharchuk.utils.WebElementUtils;

import static com.codeborne.selenide.Selenide.$x;

public class GooglePage {

    public GoogleSearchResultPage searchForText(String text) {
        $x("//input[@name='q']").clear();
        $x("//input[@name='q']").setValue(text).pressEnter();
        return new GoogleSearchResultPage();
    }

    public GooglePage openGoogleHomePage() {
        Selenide.open("https://www.google.com");
        return this;
    }

    public boolean isLanguageBlockVisible() {
        return WebElementUtils.isElementVisible("//div[@id='SIvCob']");
    }
}

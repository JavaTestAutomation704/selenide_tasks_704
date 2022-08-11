package ozakharchuk;

import com.codeborne.selenide.Selenide;
import utils.WebElementUtil;

import static com.codeborne.selenide.Selenide.$x;

public class GooglePage {

    public SearchResultPage searchFor(String text) {
        $x("//input[@name='q']").clear();
        $x("//input[@name='q']").setValue(text).pressEnter();
        return new SearchResultPage();
    }

    public GooglePage open() {
        Selenide.open("https://www.google.com");
        return this;
    }

    public boolean isLanguageBlockVisible() {
        return WebElementUtil.isElementVisible("//div[@id='SIvCob']");
    }
}

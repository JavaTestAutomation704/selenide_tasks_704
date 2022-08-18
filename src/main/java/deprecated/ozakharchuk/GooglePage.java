package deprecated.ozakharchuk;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import utils.WebElementUtil;

import static com.codeborne.selenide.Selenide.$x;

public class GooglePage {

    public SearchResultPage searchFor(String text) {
        SelenideElement searchField = $x("//input[@name='q']");
        searchField.clear();
        searchField.setValue(text).pressEnter();
        return new SearchResultPage();
    }

    public GooglePage open() {
        Selenide.open("https://www.google.com");
        return this;
    }

    public boolean isLanguageBlockVisible() {
        return WebElementUtil.isVisible("//div[@id='SIvCob']");
    }
}

package deprecated.mvynokur;

import com.codeborne.selenide.Selenide;
import com.softserveinc.ita.rozetka.utils.WebElementUtil;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;

public class GooglePage {

    public GooglePage open() {
        Selenide.open("https://www.google.com");
        return this;
    }

    public SearchResultsPage searchFor(String text) {
        $(By.name("q")).val(text).pressEnter();
        return new SearchResultsPage();
    }

    public boolean isFeelingLuckyButtonVisible() {
        return WebElementUtil.isVisible("//div[@class='FPdoLc lJ9FBc']//input[@name='btnI']");
    }

    public boolean isLanguageBlockVisible() {
        return WebElementUtil.isVisible("//div[@id='SIvCob']");
    }
}
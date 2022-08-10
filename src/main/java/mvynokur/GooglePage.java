package mvynokur;

import com.codeborne.selenide.Selenide;
import mvynokur.utils.WebElementUtils;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;

public class GooglePage {

    public GooglePage openHomePage() {
        Selenide.open("https://www.google.com");
        return this;
    }

    public SearchResultsPage searchFor(String text) {
        $(By.name("q")).val(text).pressEnter();
        return new SearchResultsPage();
    }

    public boolean isFeelingLuckyButtonVisible() {
        return WebElementUtils.isElementVisible("//div[@class='FPdoLc lJ9FBc']//input[@name='btnI']");
    }

    public boolean isLanguageBlockVisible() {
        return WebElementUtils.isElementVisible("//div[@id='SIvCob']");
    }
}
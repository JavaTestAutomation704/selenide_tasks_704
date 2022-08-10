package ytarasovych;

import com.codeborne.selenide.Selenide;

import static com.codeborne.selenide.Selenide.$x;
import static ytarasovych.utils.SelenideElementUtils.isElementDisplayed;

public class GooglePage {

    public GooglePage openHomePage() {
        Selenide.open("https://www.google.com");
        return this;
    }

    public boolean isHomePageOpen() {
        return isSettingsButtonDisplayed() && isLanguageSwitchLinkDisplayed();
    }

    public GoogleSearchResultPage search(String searchTerm) {
        $x("//input[@title]")
                .setValue(searchTerm)
                .pressEnter();
        return new GoogleSearchResultPage();
    }

    public boolean isSettingsButtonDisplayed() {
        return isElementDisplayed("//div[@jsname='LgbsSe']");
    }

    private boolean isLanguageSwitchLinkDisplayed() {
        return isElementDisplayed("//div[@id='SIvCob']");
    }
}
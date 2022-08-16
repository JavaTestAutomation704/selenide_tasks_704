package ytarasovych;

import com.codeborne.selenide.Selenide;

import static com.codeborne.selenide.Selenide.$x;
import static utils.WebElementUtil.isVisible;

public class GooglePage {

    public GooglePage open() {
        Selenide.open("https://www.google.com");
        return this;
    }

    public boolean isOpen() {
        return isSettingsButtonVisible() && isLanguageSwitchLinkVisible();
    }

    public GoogleSearchResultPage search(String searchTerm) {
        $x("//input[@title]")
                .setValue(searchTerm)
                .pressEnter();
        return new GoogleSearchResultPage();
    }

    public boolean isSettingsButtonVisible() {
        return isVisible("//div[@jsname='LgbsSe']");
    }

    private boolean isLanguageSwitchLinkVisible() {
        return isVisible("//div[@id='SIvCob']");
    }
}
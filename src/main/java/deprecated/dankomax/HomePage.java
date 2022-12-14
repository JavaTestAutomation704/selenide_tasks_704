package deprecated.dankomax;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$x;
import static com.softserveinc.ita.rozetka.utils.WebElementUtil.isVisible;


public class HomePage {
    public HomePage open() {
        Selenide.open("https://www.google.com");
        return this;
    }

    public SearchResultsPage search(String phrase) {
        SelenideElement searchField = $x("//input[@name='q']");
        searchField.clear();
        searchField.setValue(phrase).pressEnter();
        return new SearchResultsPage();
    }

    public String getPageTitleText() {
        return $x("//title").attr("text");
    }

    public boolean isSelectLanguageSectionVisible() {
        return isVisible("//div[@id='SIvCob']");
    }

    public boolean isFeelingLuckyButtonVisible() {
        return isVisible("//div[not(@jsname='VlcLAe')]/center/input[@name='btnI']");
    }

    public boolean isSettingsButtonVisible() {
        return isVisible("//div[@jsname='LgbsSe']");
    }
}
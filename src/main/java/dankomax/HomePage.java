package dankomax;

import static com.codeborne.selenide.Selenide.$x;
import static com.codeborne.selenide.Selenide.open;
import static dankomax.utils.WebElementUtils.isVisible;


public class HomePage {
    public HomePage openHomePage() {
        open("https://www.google.com");
        return this;
    }

    public SearchResultsPage search(String phrase) {
        $x("//input[@name='q']").clear();
        $x("//input[@name='q']").setValue(phrase).pressEnter();
        return new SearchResultsPage();
    }

    public String getPageTitleText() {
        return $x("//title").attr("text");
    }

    public boolean isSelectLanguageSectionVisible() {
        return isVisible($x("//div[@id='SIvCob']"));
    }

    public boolean isFeelingLuckyButtonVisible() {
        return isVisible($x("//div[not(@jsname='VlcLAe')]/center/input[@name='btnI']"));
    }

    public boolean isSettingsButtonVisible() {
        return isVisible($x("//div[@jsname='LgbsSe']"));
    }
}
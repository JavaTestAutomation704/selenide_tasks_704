package dankomax;

import static com.codeborne.selenide.Selenide.$x;


public class HomePage {
    public SearchResultsPage searchPhrase(String phrase) {
        $x("//input[@name='q']").clear();
        $x("//input[@name='q']").setValue(phrase).pressEnter();
        return new SearchResultsPage();
    }

    public String getPageTitle() {
        return $x("//title").attr("text");
    }

    public boolean isSelectLanguageSectionVisible() {
        return $x("//div[@id='SIvCob']").isDisplayed();
    }

    public boolean isFeelingLuckyButtonVisible() {
        return $x("//div[not(@jsname='VlcLAe')]/center/input[@name='btnI']").isDisplayed();
    }

    public boolean isSettingsButtonVisible() {
        return $x("//div[@jsname='LgbsSe']").isDisplayed();
    }
}
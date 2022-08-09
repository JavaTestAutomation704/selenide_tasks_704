package dankomax;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$x;


public class HomePage {
    public SearchPage searchPhrase(String phrase) {
        $x("//input[@name='q']").clear();
        $x("//input[@name='q']").setValue(phrase).pressEnter();
        return new SearchPage();
    }

    public SelenideElement title() {
        return $x("//title");
    }

    public SelenideElement languageSelection() {
        return $x("//div[@id='SIvCob']");
    }

    public SelenideElement feelingLuckyButton() {
        return $x("//div[not(@jsname='VlcLAe')]/center/input[@name='btnI']");
    }

    public SelenideElement settingsButton() {
        return $x("//div[@jsname='LgbsSe']");
    }
}
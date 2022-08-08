package dankomax;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$x;


public class HomePage {
    public SearchPage searchPhrase(String phrase) {
        $x("//input[@name='q']").clear();
        $x("//input[@name='q']").setValue(phrase).pressEnter();
        return new SearchPage();
    }

    public HomePage verifyCurrentPageIsHomePage() {
        $x("//title").shouldHave(attribute("text", "Google"));
        $x("//div[@id='SIvCob']").shouldBe(visible);
        $x("//div[not(@jsname='VlcLAe')]/center/input[@name='btnI']").shouldBe(visible).shouldHave(value("I'm Feeling Lucky"));
        $x("//div[@jsname='LgbsSe']").shouldBe(visible).shouldHave(text("Settings"));
        return this;
    }
}
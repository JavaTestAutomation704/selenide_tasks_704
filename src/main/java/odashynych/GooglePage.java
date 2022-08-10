package odashynych;

import com.codeborne.selenide.Selenide;
import odashynych.utils.WebElementUtil;

import static com.codeborne.selenide.Selenide.$x;

public class GooglePage {
    public GoogleResultPage search(String text) {
        $x("//input[@name='q']").setValue(text).pressEnter();
        return new GoogleResultPage();
    }

    public GooglePage openHomePage() {
        Selenide.open("https://www.google.com/");
        return this;
    }

    public boolean isLanguageComponentVisible(){
        return WebElementUtil.isDisplayed("//*[@id='SIvCob']");
    }
}

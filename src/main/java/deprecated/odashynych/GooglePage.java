package deprecated.odashynych;

import com.codeborne.selenide.Selenide;
import com.softserveinc.ita.rozetka.utils.WebElementUtil;

import static com.codeborne.selenide.Selenide.$x;

public class GooglePage {
    public GoogleResultPage search(String text) {
        $x("//input[@name='q']").setValue(text).pressEnter();
        return new GoogleResultPage();
    }

    public GooglePage open() {
        Selenide.open("https://www.google.com/");
        return this;
    }

    public boolean isLanguageComponentVisible() {
        return WebElementUtil.isVisible("//*[@id='SIvCob']");
    }
}

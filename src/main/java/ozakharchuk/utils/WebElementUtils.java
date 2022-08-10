package ozakharchuk.utils;

import com.codeborne.selenide.Condition;
import java.time.Duration;

import static com.codeborne.selenide.Selenide.$x;

public class WebElementUtils {

    static public boolean isElementVisible(String elementXpath) {
        try {
            return $x(elementXpath).shouldBe(Condition.visible, Duration.ofSeconds(5)).isDisplayed();
        } catch (AssertionError e) {
            return false;
        }
    }
}

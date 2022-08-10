package mvynokur.utils;

import java.time.Duration;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$x;

public class WebElementUtils {

    public static boolean isElementVisible(String elementXpath) {
        try {
            return $x(elementXpath).shouldBe(visible, Duration.ofSeconds(5)).isDisplayed();
        } catch (AssertionError e) {
            return false;
        }
    }
}

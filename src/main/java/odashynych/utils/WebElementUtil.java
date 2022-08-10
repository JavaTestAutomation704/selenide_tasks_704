package odashynych.utils;

import java.time.Duration;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$x;

public class WebElementUtil {
    public static boolean isDisplayed(String xpath) {
        try {
            return $x(xpath).shouldBe(visible, Duration.ofSeconds(5)).isDisplayed();
        } catch (AssertionError e) {
            return false;
        }
    }
}

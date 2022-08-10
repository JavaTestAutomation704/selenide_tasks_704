package ytarasovych.utils;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$x;
import static java.time.Duration.ofSeconds;

public class SelenideElementUtils {

    public static boolean isElementDisplayed(String elementXpath) {
        try {
            return $x(elementXpath)
                    .shouldBe(visible, ofSeconds(5))
                    .isDisplayed();
        } catch (AssertionError e) {
            return false;
        }
    }
}
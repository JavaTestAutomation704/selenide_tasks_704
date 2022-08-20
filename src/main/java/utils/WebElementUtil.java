package utils;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import static com.codeborne.selenide.CollectionCondition.sizeGreaterThanOrEqual;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$$x;
import static com.codeborne.selenide.Selenide.$x;


public class WebElementUtil {
    private static final Duration TIMEOUT = Duration.ofSeconds(5);

    public static boolean isVisible(String elementXpath) {
        try {
            return $x(elementXpath).shouldBe(visible, TIMEOUT).isDisplayed();
        } catch (AssertionError e) {
            return false;
        }
    }

    public static int getCollectionSize(String elementsXpath) {
        try {
            return $$x(elementsXpath).shouldBe(sizeGreaterThanOrEqual(1), TIMEOUT).size();
        } catch (AssertionError e) {
            return 0;
        }
    }

    public static String getText(String elementXpath) {
        if (isVisible(elementXpath)) {
            return $x(elementXpath).text().toLowerCase();
        }
        return "";
    }
}
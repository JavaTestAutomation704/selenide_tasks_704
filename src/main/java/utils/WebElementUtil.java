package utils;

import lombok.experimental.UtilityClass;

import java.time.Duration;

import static com.codeborne.selenide.CollectionCondition.sizeGreaterThanOrEqual;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$x;
import static com.codeborne.selenide.Selenide.$$x;

@UtilityClass
public class WebElementUtil {
    private static final Duration TIMEOUT = Duration.ofSeconds(10);

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
            return $x(elementXpath).text();
        }
        return "";
    }

    public static long getLong(String elementXpath) {
        return Long.parseLong($x(elementXpath).shouldBe(visible, TIMEOUT)
                .text()
                .replaceAll("[^0-9]", ""));
    }
}
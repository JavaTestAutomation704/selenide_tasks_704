package utils;

import lombok.experimental.UtilityClass;

import java.time.Duration;

import static com.codeborne.selenide.CollectionCondition.sizeGreaterThanOrEqual;
import static com.codeborne.selenide.Condition.cssValue;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$$x;
import static com.codeborne.selenide.Selenide.$x;

@UtilityClass
public class WebElementUtil {
    private static final Duration TIMEOUT = Duration.ofSeconds(10);

    public static boolean isVisible(String elementXpath) {
        return isVisible(elementXpath, TIMEOUT.getSeconds());
    }

    public static boolean isVisible(String elementXpath, long seconds) {
        try {
            return $x(elementXpath).shouldBe(visible, Duration.ofSeconds(seconds)).isDisplayed();
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

    public static void waitTillVisible(String elementXpath) {
        isVisible(elementXpath);
    }

    public static long getLong(String elementXpath) {
        return Long.parseLong($x(elementXpath).shouldBe(visible, TIMEOUT)
                .text()
                .replaceAll("[^0-9]", ""));
    }

    public static String getBorderColor(String elementXpath, String colorRGB) {
        String color = String.format("rgb(%s)", colorRGB);
        try {
            return $x(elementXpath)
                    .shouldHave(cssValue("border-color", color))
                    .getCssValue("border-color");
        } catch (AssertionError e) {
            return "";
        }
    }
}
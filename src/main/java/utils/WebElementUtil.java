package utils;

import com.codeborne.selenide.Condition;
import lombok.experimental.UtilityClass;

import java.time.Duration;

import static com.codeborne.selenide.CollectionCondition.sizeGreaterThanOrEqual;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$$x;
import static com.codeborne.selenide.Selenide.$x;

@UtilityClass
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
            return $x(elementXpath).text();
        }
        return "";
    }

    public static String getBorderColor(String elementXpath, String color) {
        try {
            return $x(elementXpath)
                    .shouldHave(Condition.cssValue("border-color", color))
                    .getCssValue("border-color");
        } catch (AssertionError e) {
            return "";
        }
    }
}
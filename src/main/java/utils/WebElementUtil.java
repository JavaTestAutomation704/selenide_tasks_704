package utils;

import lombok.experimental.UtilityClass;

import java.time.Duration;

import static com.codeborne.selenide.CollectionCondition.sizeGreaterThanOrEqual;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$$x;
import static com.codeborne.selenide.Selenide.$x;

@UtilityClass
public class WebElementUtil {
    private final Duration TIMEOUT = Duration.ofSeconds(5);

    public boolean isVisible(String elementXpath) {
        try {
            return $x(elementXpath).shouldBe(visible, TIMEOUT).isDisplayed();
        } catch (AssertionError e) {
            return false;
        }
    }

    public int getCollectionSize(String elementsXpath) {
        try {
            return $$x(elementsXpath).shouldBe(sizeGreaterThanOrEqual(1), TIMEOUT).size();
        } catch (AssertionError e) {
            return 0;
        }
    }
}
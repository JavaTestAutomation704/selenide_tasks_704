package utils;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import java.time.Duration;

import static com.codeborne.selenide.CollectionCondition.sizeGreaterThanOrEqual;
import static com.codeborne.selenide.Condition.visible;


public class WebElementUtil {
    private static final Duration TIMEOUT = Duration.ofSeconds(2);

    public static boolean isVisible(SelenideElement element) {
        try {
            return element.shouldBe(visible, TIMEOUT).isDisplayed();
        } catch (AssertionError e) {
            return false;
        }
    }

    public static int getCollectionSize(ElementsCollection elements) {
        try {
            return elements.shouldBe(sizeGreaterThanOrEqual(1), TIMEOUT).size();
        } catch (AssertionError e) {
            return 0;
        }
    }
}
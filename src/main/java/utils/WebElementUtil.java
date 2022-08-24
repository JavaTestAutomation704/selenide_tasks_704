package utils;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import lombok.experimental.UtilityClass;

import java.time.Duration;
import java.util.List;

import static com.codeborne.selenide.CollectionCondition.sizeGreaterThanOrEqual;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$x;
import static com.codeborne.selenide.Selenide.$$x;

@UtilityClass
public class WebElementUtil {
    private static final Duration TIMEOUT = Duration.ofSeconds(10);

    public static boolean isVisible(String elementXpath) {
        try {
            return waitVisibility(elementXpath).isDisplayed();
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
        return Long.parseLong( waitVisibility(elementXpath)
                .text()
                .replaceAll("[^0-9]",""));
    }

    public static SelenideElement waitVisibility(String elementXpath) {
        return $x(elementXpath).shouldBe(visible, TIMEOUT);
    }

    public static SelenideElement waitVisibility(SelenideElement element) {
        return element.shouldBe(visible, TIMEOUT);
    }

    public static SelenideElement waitInvisibility(String elementXpath) {
        return $x(elementXpath).shouldNotBe(visible, TIMEOUT);
    }

    public static SelenideElement waitTextChange(String elementXpath, String text) {
        return $x(elementXpath).shouldNotBe(Condition.text(text), TIMEOUT);
    }

    public static List<SelenideElement> waitCollection(String elementsXpath) {
        return $$x(elementsXpath).shouldBe(sizeGreaterThanOrEqual(1));
    }
}
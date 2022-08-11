package utils;

import com.codeborne.selenide.CollectionCondition;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$$x;
import static com.codeborne.selenide.Selenide.$x;
import static java.time.Duration.ofSeconds;

public class WebElementUtil {

    public static boolean isElementVisible(String elementXpath) {
        try {
            return $x(elementXpath)
                    .shouldBe(visible, ofSeconds(5))
                    .isDisplayed();
        } catch (AssertionError e) {
            return false;
        }
    }

    public static int getCollectionSize(String elementsXpath) {
        try {

            return $$x(elementsXpath)
                    .shouldHave(CollectionCondition.sizeGreaterThan(1), ofSeconds(5))
                    .size();
        } catch (AssertionError e) {
            return 0;
        }
    }
}

package com.softserveinc.ita.rozetka.utils;

import lombok.experimental.UtilityClass;

import java.time.Duration;

import static com.codeborne.selenide.CollectionCondition.sizeGreaterThanOrEqual;
import static com.codeborne.selenide.Condition.cssValue;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverConditions.urlContaining;

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
        isVisible(elementXpath, 5);
    }

    public static long getNumber(String elementXpath) {
        return Long.parseLong($x(elementXpath).shouldBe(visible, TIMEOUT)
                .text()
                .replaceAll("[^0-9]", ""));
    }

    public static boolean isBorderColorCorrect(String elementXpath, String colorRgb) {
        String expectedColor = String.format("rgb(%s)", colorRgb);
        try {
            String actualColor = $x(elementXpath)
                    .shouldHave(cssValue("border-color", expectedColor))
                    .getCssValue("border-color");
            return actualColor.equals(expectedColor);
        } catch (AssertionError e) {
            return false;
        }
    }

    public static long getLongFromField(String elementXpath) {
        var value = $x(elementXpath).val();
        if (value != null) {
            return Long.parseLong(value);
        }
        return 0;
    }

    public static void waitUntilUrlContains(String charSequence) {
        webdriver().shouldHave(urlContaining(charSequence), TIMEOUT);
    }
}
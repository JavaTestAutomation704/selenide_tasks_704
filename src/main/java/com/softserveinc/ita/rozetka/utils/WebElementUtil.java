package com.softserveinc.ita.rozetka.utils;

import com.codeborne.selenide.SelenideElement;
import lombok.experimental.UtilityClass;

import java.time.Duration;

import static com.codeborne.selenide.CollectionCondition.sizeGreaterThanOrEqual;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverConditions.urlContaining;
import static java.lang.String.format;
import static java.time.Duration.ofSeconds;

@UtilityClass
public class WebElementUtil {
    private static final Duration TIMEOUT = ofSeconds(10);

    public static boolean isVisible(String elementXpath) {
        return isVisible(elementXpath, TIMEOUT.getSeconds());
    }

    public static boolean isVisible(String elementXpath, long seconds) {
        try {
            return $x(elementXpath).shouldBe(visible, ofSeconds(seconds)).isDisplayed();
        } catch (AssertionError e) {
            return false;
        }
    }

    public static boolean isVisible(SelenideElement element) {
        return isVisible(element, TIMEOUT.getSeconds());
    }

    public static boolean isVisible(SelenideElement element, long seconds) {
        try {
            return element.shouldBe(visible, ofSeconds(seconds)).isDisplayed();
        } catch (AssertionError e) {
            return false;
        }
    }

    public static boolean hasCssValue(String elementXpath, String property, String cssValue) {
        try {
            $x(elementXpath).shouldHave(cssValue(property, cssValue));
            return true;
        } catch (AssertionError e) {
            return false;
        }
    }

    public static boolean hasAttribute(String elementXpath, String name, String value) {
        try {
            $x(elementXpath).shouldHave(attribute(name, value));
            return true;
        } catch (AssertionError e) {
            return false;
        }
    }

    public static int getCollectionSize(String elementsXpath, long seconds) {
        try {
            return $$x(elementsXpath).shouldBe(sizeGreaterThanOrEqual(1), ofSeconds(seconds)).size();
        } catch (AssertionError e) {
            return 0;
        }
    }

    public static int getCollectionSize(String elementsXpath) {
        return getCollectionSize(elementsXpath, TIMEOUT.getSeconds());
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

    public static void waitForTextChange(String elementXpath, String elementText) {
        try {
            $x(elementXpath).shouldNotHave(text(elementText));
        } catch (AssertionError ignore) {
        }
    }

    public static void waitTillPreloaderInvisible() {
        var preloaderXpath = "//main[contains(@class, 'preloader_type_element')]";
        if (isVisible(preloaderXpath, 10)) {
            $x(preloaderXpath).shouldNotBe(visible, Duration.ofSeconds(10));
        }
    }

    public static long getNumber(String elementXpath) {
        return getNumber($x(elementXpath));
    }

    public static long getNumber(SelenideElement element) {
        return Long.parseLong(element.shouldBe(visible, TIMEOUT)
                .text()
                .replaceAll("[^0-9]", ""));
    }

    public static boolean isBorderColorCorrect(String elementXpath, String colorRgb) {
        var expectedColor = format("rgb(%s)", colorRgb);
        try {
            var actualColor = $x(elementXpath)
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

    public static void waitText(SelenideElement element, String text) {
        try {
            element.shouldHave(text(text), TIMEOUT);
        } catch (AssertionError ignore) {
        }
    }

    public static void waitInvisibility(SelenideElement element) {
        try {
            element.shouldNotBe(visible, TIMEOUT);
        } catch (AssertionError ignore) {
        }
    }

    public static void waitInvisibility(String elementXpath) {
        waitInvisibility($x(elementXpath));
    }
}
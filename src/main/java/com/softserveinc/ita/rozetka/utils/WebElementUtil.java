package com.softserveinc.ita.rozetka.utils;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.SelenideElement;
import lombok.experimental.UtilityClass;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import static com.codeborne.selenide.CollectionCondition.sizeGreaterThanOrEqual;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverConditions.urlContaining;
import static java.lang.String.format;
import static java.time.Duration.ofSeconds;

@UtilityClass
public class WebElementUtil {
    private static final Duration TIMEOUT = ofSeconds(15);

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

    public static boolean hasAttributeValue(String elementXpath, String name, String value) {
        try {
            $x(elementXpath).shouldHave(attribute(name, value));
            return true;
        } catch (AssertionError e) {
            return false;
        }
    }

    public static boolean hasAttribute(String elementXpath, String name) {
        try {
            $x(elementXpath).shouldHave(attribute(name));
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

    public static String getAttribute(String elementXpath, String attributeName) {
        if (isVisible(elementXpath)) {
            return $x(elementXpath).getAttribute(attributeName);
        }
        return "";
    }

    public static List<String> getElementsText(String elementsXpath) {
        try {
            return $$x(elementsXpath)
                    .shouldBe(sizeGreaterThanOrEqual(1), ofSeconds(TIMEOUT.getSeconds()))
                    .texts();
        } catch (AssertionError e) {
            return new ArrayList<>();
        }
    }

    public static void waitTillVisible(String elementXpath) {
        isVisible(elementXpath, 5);
    }

    public static void waitTillVisible(String elementXpath, long seconds) {
        isVisible(elementXpath, seconds);
    }

    public static void waitForTextChange(String elementXpath, String elementText) {
        try {
            $x(elementXpath).shouldNotHave(text(elementText));
        } catch (AssertionError ignore) {
        }
    }

    public static void waitCollectionSizeChange(String elementsXpath, int size) {
        try {
            $$x(elementsXpath).shouldBe(CollectionCondition.sizeNotEqual(size), ofSeconds(TIMEOUT.getSeconds()));
        } catch (AssertionError ignore) {
        }
    }

    public static void waitCollectionSizeIncrease(String elementsXpath, int size) {
        try {
            $$x(elementsXpath).shouldBe(CollectionCondition.sizeGreaterThan(size), ofSeconds(TIMEOUT.getSeconds()));
        } catch (AssertionError ignore) {
        }
    }

    public static void waitCollectionSizeDecrease(String elementsXpath, int size) {
        try {
            $$x(elementsXpath).shouldBe(CollectionCondition.sizeLessThan(size), ofSeconds(TIMEOUT.getSeconds()));
        } catch (AssertionError ignore) {
        }
    }

    public static void waitTillPreloaderInvisible() {
        var preloaderXpath = "//main[contains(@class, 'preloader_type_element')]";
        if (isVisible(preloaderXpath)) {
            waitInvisibility(preloaderXpath, 30);
        }
    }

    public static void waitTillCheckoutPreloaderInvisible() {
        var checkoutPreloaderXpath = "//rz-checkout-main/section[@class = 'checkout-layout preloader_type_element']";
        if (isVisible(checkoutPreloaderXpath)) {
            waitInvisibility(checkoutPreloaderXpath);
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

    public static boolean isColorCorrect(String elementXpath, String propertyName, String colorRgb) {
        var expectedColor = format("rgb(%s)", colorRgb);
        try {
            var actualColor = $x(elementXpath)
                    .shouldHave(cssValue(propertyName, expectedColor))
                    .getCssValue(propertyName);
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
        waitInvisibility(element, TIMEOUT.getSeconds());
    }

    public static void waitInvisibility(SelenideElement element, long timeout) {
        try {
            element.shouldNotBe(visible, ofSeconds(timeout));
        } catch (AssertionError ignore) {
        }
    }

    public static void waitInvisibility(String elementXpath) {
        waitInvisibility($x(elementXpath));
    }

    public static void waitInvisibility(String elementXpath, long timeout) {
        waitInvisibility($x(elementXpath), timeout);
    }

    public static void waitForAttributeValue(String elementXpath, String attributeName, String attributeValue) {
        try {
            $x(elementXpath).shouldHave(attribute(attributeName, attributeValue));
        } catch (AssertionError ignore) {
        }
    }
}
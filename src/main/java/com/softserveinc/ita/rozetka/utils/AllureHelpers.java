package com.softserveinc.ita.rozetka.utils;

import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.WebDriverRunner;
import io.qameta.allure.Attachment;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.nio.charset.StandardCharsets;

public final class AllureHelpers {
    @Attachment(value = "Html source", type = "text/html", fileExtension = ".html")
    public static byte[] getPageSource() {
        return getPageSourceBytes();
    }

    @Attachment(value = "Screenshot", type = "image/png", fileExtension = ".png")
    public static byte[] takeScreenshot() {
        return getScreenshotBytes();
    }

    @Attachment(value = "{name}", type = "image/png", fileExtension = ".png")
    public static byte[] takeScreenshot(final String name) {
        return getScreenshotBytes();
    }

    @Attachment(value = "Element screenshot", type = "image/png", fileExtension = ".png")
    public static byte[] takeScreenshot(final SelenideElement elem) {
        return getScreenshotBytes(elem);
    }

    public static byte[] getPageSourceBytes() {
        return WebDriverRunner
                .getWebDriver()
                .getPageSource()
                .getBytes(StandardCharsets.UTF_8);
    }

    public static byte[] getScreenshotBytes() {
        return ((TakesScreenshot) WebDriverRunner.getWebDriver()).getScreenshotAs(OutputType.BYTES);
    }

    public static byte[] getScreenshotBytes(final SelenideElement elem) {
        return elem.getScreenshotAs(OutputType.BYTES);
    }
}
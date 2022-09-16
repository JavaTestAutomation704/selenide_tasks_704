package com.softserveinc.ita.rozetka.utils;

import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.closeWindow;
import static com.codeborne.selenide.Selenide.switchTo;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

public class BrowserTabUtil {

    @Step("Browser tab util: close current tab and switch to {tabIndex}")
    public static void closeCurrentTabAndSwitchTo(int tabIndex) {
        closeWindow();
        switchTo().window(tabIndex);
    }

    @Step("Browser tab util: switch to tab {pageName}")
    public static void switchToTab(String pageName) {
        for (var handle : getWebDriver().getWindowHandles()) {
            var webDriver = switchTo().window(handle);
            if (webDriver.getTitle().contains(pageName)) {
                break;
            }
        }
    }
}
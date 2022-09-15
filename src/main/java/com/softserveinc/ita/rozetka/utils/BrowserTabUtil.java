package com.softserveinc.ita.rozetka.utils;

import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.closeWindow;
import static com.codeborne.selenide.Selenide.switchTo;

public class BrowserTabUtil {

    @Step("Browser tab util: back")
    public static void back() {
        closeWindow();
        switchTo().window(0);
    }
}
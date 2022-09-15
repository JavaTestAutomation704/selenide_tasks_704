package com.softserveinc.ita.rozetka.utils;

import com.softserveinc.ita.rozetka.components.SocialNetworksSection;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.closeWindow;
import static com.codeborne.selenide.Selenide.switchTo;

public class BrowserTabUtil {

    @Step("Browser tab util: back to the previous tab")
    public static SocialNetworksSection backToPreviousTab() {
        closeWindow();
        switchTo().window(0);
        return new SocialNetworksSection();
    }
}
package com.softserveinc.ita.rozetka.components;

import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$x;

public class HelpCenterHeader {

    @Step("Help center header: open help center search menu")
    public HelpCenterSearchMenu openHelpCenterSearchMenu(String keyword) {
        $x("//input[@id = 'ss-input']").val(keyword).click();
        return new HelpCenterSearchMenu();
    }
}

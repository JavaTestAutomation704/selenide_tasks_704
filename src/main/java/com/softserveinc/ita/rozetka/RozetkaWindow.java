package com.softserveinc.ita.rozetka;

import com.softserveinc.ita.rozetka.components.Header;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.closeWindow;
import static com.codeborne.selenide.Selenide.switchTo;

public class RozetkaWindow extends BasePage {

    @Override
    @Step("Rozetka window: back")
    public Header back() {
        closeWindow();
        switchTo().window(0);
        return new Header();
    }
}
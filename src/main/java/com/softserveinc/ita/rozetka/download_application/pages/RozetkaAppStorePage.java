package com.softserveinc.ita.rozetka.download_application.pages;

import io.qameta.allure.Step;

import static com.softserveinc.ita.rozetka.utils.WebElementUtil.isVisible;

public class RozetkaAppStorePage extends DownloadApplicationBasePage {

    @Step("Rozetka app store page: open")
    public RozetkaAppStorePage open() {
        open("apple");
        return this;
    }

    @Override
    public boolean isOpened() {
        return isVisible("(//a[contains(@href, 'apple.com') and contains(@href, 'rozetka')])[1]");
    }
}
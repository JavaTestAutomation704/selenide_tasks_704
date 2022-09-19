package com.softserveinc.ita.rozetka.download_application.pages;

import io.qameta.allure.Step;

import static com.softserveinc.ita.rozetka.utils.WebElementUtil.isVisible;

public class RozetkaGooglePlayPage extends DownloadApplicationBasePage {

    @Step("Rozetka google play page: open")
    public RozetkaGooglePlayPage open() {
        open("play.google");
        return this;
    }

    @Override
    public boolean isOpened() {
        return isVisible("//title[contains(text(), 'Google Play')]/ancestor::html//a[contains(@href, 'id=Rozetka')]");
    }
}
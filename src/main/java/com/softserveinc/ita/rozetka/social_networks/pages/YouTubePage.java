package com.softserveinc.ita.rozetka.social_networks.pages;

import io.qameta.allure.Step;

import static com.softserveinc.ita.rozetka.utils.WebElementUtil.isVisible;

public class YouTubePage extends BaseSocialNetworkPage {

    @Step("YouTube page: open")
    public YouTubePage open() {
        open("YouTube");
        return this;
    }

    @Override
    public boolean isOpened() {
        return isVisible("//tp-yt-app-header//*[@title and text()='ROZETKA']");
    }
}
package com.softserveinc.ita.rozetka.social_networks.pages;

import io.qameta.allure.Step;

import static com.softserveinc.ita.rozetka.utils.WebElementUtil.isVisible;

public class TwitterPage extends BaseSocialNetworkPage {

    @Step("Twitter page: open")
    public TwitterPage open() {
        open("Twitter");
        return this;
    }

    @Override
    public boolean isOpened() {
        return isVisible("//title[contains(text(), 'Rozetka')]" +
                "/ancestor::html//span[contains(text(), 'Twitter Rozetka')]");
    }
}
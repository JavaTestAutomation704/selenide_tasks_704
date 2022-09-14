package com.softserveinc.ita.rozetka.social_networks.pages;

import io.qameta.allure.Step;

import static com.softserveinc.ita.rozetka.utils.WebElementUtil.isVisible;

public class FacebookPage extends BaseSocialNetworkPage {

    @Step("Facebook page: open")
    public FacebookPage open() {
        open("Facebook");
        return this;
    }

    @Override
    public boolean isOpened() {
        return isVisible("//title[contains(text(), 'Facebook')]/ancestor::html//h1[text()='ROZETKA']");
    }
}
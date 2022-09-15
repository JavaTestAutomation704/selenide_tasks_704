package com.softserveinc.ita.rozetka.social_networks.pages;

import io.qameta.allure.Step;

import static com.softserveinc.ita.rozetka.utils.WebElementUtil.isVisible;

public class InstagramPage extends SocialNetworkBasePage {

    @Step("Instagram page: open")
    public InstagramPage open() {
        open("Instagram");
        return this;
    }

    @Override
    public boolean isOpened() {
        return isVisible("//title[contains(text(), 'Instagram')]/ancestor::html//h2[text()='rozetkaua']");
    }
}
package com.softserveinc.ita.rozetka.social_networks.pages;

import io.qameta.allure.Step;

import static com.softserveinc.ita.rozetka.utils.WebElementUtil.isVisible;

public class ViberPage extends SocialNetworkBasePage {

    @Step("Viber page: open")
    public ViberPage open() {
        open("Viber");
        return this;
    }

    @Override
    public boolean isOpened() {
        return isVisible("//vbr-content//h2[text()='ROZETKA']");
    }
}
package com.softserveinc.ita.rozetka.social_networks.pages;

import io.qameta.allure.Step;

import static com.softserveinc.ita.rozetka.utils.WebElementUtil.isVisible;

public class TelegramPage extends BaseSocialNetworkPage {

    @Step("Telegram page: open")
    public TelegramPage open() {
        open("Telegram");
        return this;
    }

    @Override
    public boolean isOpened() {
        return isVisible("//div[@class='tgme_page_title']//span[text()='ROZETKA']");
    }
}
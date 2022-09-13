package com.softserveinc.ita.rozetka.social_networks.pages;

import static com.softserveinc.ita.rozetka.utils.WebElementUtil.isVisible;

public class TelegramPage extends SocialNetworkPage {

    public TelegramPage(String pageName) {
        super(pageName);
    }

    @Override
    public boolean isOpened() {
        return isVisible("//div[@class='tgme_page']");
    }
}
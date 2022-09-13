package com.softserveinc.ita.rozetka.social_networks.pages;

import static com.softserveinc.ita.rozetka.utils.WebElementUtil.isVisible;

public class TwitterPage extends SocialNetworkPage {

    public TwitterPage(String pageName) {
        super(pageName);
    }

    @Override
    public boolean isOpened() {
        return isVisible("//a[text()='twitter.com/rozetka_deals']");
    }
}
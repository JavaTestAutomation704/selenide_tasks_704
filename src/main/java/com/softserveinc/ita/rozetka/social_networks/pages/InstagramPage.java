package com.softserveinc.ita.rozetka.social_networks.pages;

import static com.softserveinc.ita.rozetka.utils.WebElementUtil.isVisible;

public class InstagramPage extends SocialNetworkPage {

    public InstagramPage(String pageName) {
        super(pageName);
    }

    @Override
    public boolean isOpened() {
        return isVisible("//*[local-name() = 'svg' and @aria-label='Instagram']");
    }
}
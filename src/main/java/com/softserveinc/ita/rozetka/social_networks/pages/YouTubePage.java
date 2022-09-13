package com.softserveinc.ita.rozetka.social_networks.pages;

import static com.softserveinc.ita.rozetka.utils.WebElementUtil.isVisible;

public class YouTubePage extends SocialNetworkPage {

    public YouTubePage(String pageName) {
        super(pageName);
    }

    @Override
    public boolean isOpened() {
        return isVisible("//ytd-channel-name//*[@title and text()='ROZETKA']");
    }
}
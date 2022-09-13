package com.softserveinc.ita.rozetka.social_networks.pages;

import static com.softserveinc.ita.rozetka.utils.WebElementUtil.isVisible;

public class FacebookPage extends SocialNetworkPage {

    public FacebookPage(String pageName) {
        super(pageName);
    }

    @Override
    public boolean isOpened() {
        return isVisible("//span[text()='See more of ROZETKA on Facebook']");
    }
}
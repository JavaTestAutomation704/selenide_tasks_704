package com.softserveinc.ita.rozetka.social_networks.pages;

import static com.softserveinc.ita.rozetka.utils.WebElementUtil.isVisible;

public class ViberPage extends SocialNetworkPage {

    public ViberPage(String pageName) {
        super(pageName);
    }

    @Override
    public boolean isOpened() {
        return isVisible("//vbr-header//p[text()='ROZETKA']");
    }
}
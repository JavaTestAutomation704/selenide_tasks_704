package com.softserveinc.ita.rozetka.social_networks.pages;

import com.softserveinc.ita.rozetka.components.SocialNetworksSection;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$x;
import static com.softserveinc.ita.rozetka.utils.BrowserTabUtil.closeCurrentTabAndSwitchTo;
import static com.softserveinc.ita.rozetka.utils.BrowserTabUtil.switchToTab;
import static java.lang.String.format;

public abstract class SocialNetworkBasePage {

    @Step("Social network base page: open {pageName}")
    protected SocialNetworkBasePage open(String pageName) {
        $x(format("//a[@title='%s']", pageName)).click();
        switchToTab(pageName);
        return this;
    }

    abstract protected boolean isOpened();

    @Step("Social network base page: back to the first tab")
    public SocialNetworksSection backToFirstTab() {
        closeCurrentTabAndSwitchTo(0);
        return new SocialNetworksSection();
    }
}
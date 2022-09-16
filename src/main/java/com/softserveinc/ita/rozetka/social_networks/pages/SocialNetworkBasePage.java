package com.softserveinc.ita.rozetka.social_networks.pages;

import com.softserveinc.ita.rozetka.components.SocialNetworksSection;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$x;
import static com.softserveinc.ita.rozetka.utils.BrowserTabUtil.closeCurrentTabAndSwitchToFirstTab;
import static com.softserveinc.ita.rozetka.utils.BrowserTabUtil.switchToTabByPageName;
import static java.lang.String.format;

public abstract class SocialNetworkBasePage {

    @Step("Social network base page: open {pageName}")
    protected SocialNetworkBasePage open(String pageName) {
        $x(format("//a[@title='%s']", pageName)).click();
        switchToTabByPageName(pageName);
        return this;
    }

    abstract protected boolean isOpened();

    @Step("Social network base page: back to the first tab")
    public SocialNetworksSection backToFirstTab() {
        closeCurrentTabAndSwitchToFirstTab();
        return new SocialNetworksSection();
    }
}
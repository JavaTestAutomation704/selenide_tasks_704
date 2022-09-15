package com.softserveinc.ita.rozetka.social_networks.pages;

import com.softserveinc.ita.rozetka.components.SocialNetworksSection;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$x;
import static com.codeborne.selenide.Selenide.switchTo;
import static com.softserveinc.ita.rozetka.utils.BrowserTabUtil.back;
import static java.lang.String.format;

public abstract class SocialNetworkBasePage {

    @Step("Base social network page: open {pageName}")
    protected SocialNetworkBasePage open(String pageName) {
        $x(format("//a[@title='%s']", pageName)).click();
        switchTo().window(1);
        return this;
    }

    abstract protected boolean isOpened();

    public static SocialNetworksSection backToPreviousTab() {
        back();
        return new SocialNetworksSection();
    }
}
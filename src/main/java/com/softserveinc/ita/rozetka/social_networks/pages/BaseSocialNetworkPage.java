package com.softserveinc.ita.rozetka.social_networks.pages;

import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$x;
import static com.codeborne.selenide.Selenide.switchTo;
import static java.lang.String.format;

public abstract class BaseSocialNetworkPage {

    @Step("Base social network page: open {pageName}")
    protected BaseSocialNetworkPage open(String pageName) {
        $x(format("//a[@title='%s']", pageName)).click();
        switchTo().window(1);
        return this;
    }

    abstract protected boolean isOpened();
}
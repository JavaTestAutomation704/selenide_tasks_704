package com.softserveinc.ita.rozetka.social_networks.pages;

import io.qameta.allure.Step;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import static com.codeborne.selenide.Selenide.$x;
import static com.codeborne.selenide.Selenide.switchTo;
import static java.lang.String.format;

@RequiredArgsConstructor
@Getter
public abstract class SocialNetworkPage {

    private final String name;

    @Step("Social network page: open")
    public SocialNetworkPage open() {
        $x(format("//a[@title='%s']", name)).click();
        switchTo().window(1);
        return this;
    }

    public abstract boolean isOpened();
}
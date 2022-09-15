package com.softserveinc.ita.rozetka;

import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$x;

public class MyOrdersPage {

    @Step("My order page: open profile page")
    public ProfilePage openProfilePage() {
        $x("//div[contains(@class,'cabinet-user')]/a").click();
        return new ProfilePage();
    }
}

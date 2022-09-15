package com.softserveinc.ita.rozetka;

import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$x;

public class OrderCabinetPage {

    @Step("Order cabinet page: open personal cabinet page")
    public PersonalCabinetPage openPersonalCabinetPage() {
        $x("//div[contains(@class,'cabinet-user')]/a").click();
        return new PersonalCabinetPage();
    }
}

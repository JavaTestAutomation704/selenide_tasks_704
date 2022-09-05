package com.softserveinc.ita.rozetka.components;

import com.codeborne.selenide.Condition;
import com.softserveinc.ita.rozetka.HomePage;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$x;
import static com.softserveinc.ita.rozetka.utils.WebElementUtil.isVisible;

public class SmallCart {

    public boolean isOpen() {
        return isVisible("//rz-app-small-cart");
    }

    @Step("Small cart: close small cart")
    public HomePage close() {
        String closeButtonXpath = "//button[contains(@class, 'main-notification__close')]";
        $x(closeButtonXpath).shouldBe(Condition.visible);
        $x(closeButtonXpath).click();
        return new HomePage();
    }
}

package com.softserveinc.ita.rozetka.components;

import com.codeborne.selenide.Condition;
import com.softserveinc.ita.rozetka.HomePage;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$x;
import static com.softserveinc.ita.rozetka.utils.WebElementUtil.isVisible;

public class SmallCart {

    public boolean isOpened() {
        return isVisible("//rz-app-small-cart");
    }

    @Step("Small cart: close small cart")
    public HomePage close() {
        $x("//button[contains(@class, 'main-notification__close')]").shouldBe(Condition.visible).click();
        return new HomePage();
    }

    public int getProductsQuantity() {
        return Integer.parseInt($x("//span[contains(@class, 'main-notification__heading-main')]")
                .getText()
                .replaceAll("[^0-9]", ""));
    }

    public long getTotalPrice() {
        return Integer.parseInt($x("//span[@class= 'main-notification__heading-caption']//span[2]").getText());
    }
}
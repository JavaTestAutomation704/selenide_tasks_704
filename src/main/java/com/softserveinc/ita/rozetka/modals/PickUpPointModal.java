package com.softserveinc.ita.rozetka.modals;

import com.softserveinc.ita.rozetka.CheckoutPage;
import com.softserveinc.ita.rozetka.data.DeliveryService;
import com.softserveinc.ita.rozetka.utils.WebElementUtil;
import io.qameta.allure.Step;

import static com.codeborne.selenide.ClickOptions.usingJavaScript;
import static com.codeborne.selenide.Selenide.$x;
import static com.softserveinc.ita.rozetka.utils.WebElementUtil.waitTillCheckoutPreloaderInvisible;
import static java.lang.String.format;

public class PickUpPointModal {

    public String getPickUpPointName() {
        return $x("//li[contains(@class,'active')]//div[@class='map-popup__section']").text();
    }

    @Step("Pickup point modal: focus on pickup point delivery service {deliveryService} department number {departmentNumber}")
    public PickUpPointModal focusOnPickUpPoint(DeliveryService deliveryService, int departmentNumber) {
        $x(format("//p[@class='map-popup__section-heading' and contains(text(),'%s №%d ')]",
                deliveryService.getNameUa(), departmentNumber))
                .scrollIntoView(true)
                .click(usingJavaScript());
        waitTillCheckoutPreloaderInvisible();
        return this;
    }

    @Step("Pickup point modal: select pickup point and open checkout page")
    public CheckoutPage selectActivePickUpPoint() {
        $x("//li//button[contains(@class,'map-popup__submit')]").click();
        return new CheckoutPage();
    }
}
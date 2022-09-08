package com.softserveinc.ita.rozetka.modals;

import com.softserveinc.ita.rozetka.CheckoutPage;
import com.softserveinc.ita.rozetka.data.DeliveryService;
import io.qameta.allure.Step;

import static com.codeborne.selenide.ClickOptions.usingJavaScript;
import static com.codeborne.selenide.Selenide.$x;
import static java.lang.String.format;

public class PickUpPointModal {

    public String getTextAtActivePickUpPoint() {
        return $x("//li[contains(@class,'active')]//div[@class='map-popup__section']").text();
    }

    @Step("Pickup point modal: focus on pickup point delivery service {deliveryService} department №{departmentNumber}")
    public PickUpPointModal focusOnPickUpPoint(DeliveryService deliveryService, int departmentNumber) {
        $x(format("//p[@class='map-popup__section-heading' and contains(text(),'%s №%d ')]",
                deliveryService.getNameUa(), departmentNumber))
                .scrollIntoView(true)
                .click(usingJavaScript());
        return this;
    }

    @Step("Pickup point modal: select pickup point and open checkout page")
    public CheckoutPage selectActivePickUpPoint() {
        $x("//li//button[contains(@class,'map-popup__submit')]").click();
        return new CheckoutPage();
    }
}

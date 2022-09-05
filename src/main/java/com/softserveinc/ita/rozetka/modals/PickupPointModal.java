package com.softserveinc.ita.rozetka.modals;

import com.softserveinc.ita.rozetka.CheckoutPage;

import static com.codeborne.selenide.ClickOptions.usingJavaScript;
import static com.codeborne.selenide.Selenide.$x;

public class PickupPointModal {

    public String getTextAtActivePickupPoint() {
        return $x("//li[contains(@class,'active')]//div[@class='map-popup__section']").text();
    }

    public PickupPointModal moveToPickupPoint(String postOffice, int number) {
        $x(String.format("(//p[@class='map-popup__section-heading' and contains(text(),'%s')])[%d]",
                postOffice, number)).click(usingJavaScript());
        return this;
    }

    public CheckoutPage selectActivePickupPoint() {
        $x("//li//button[contains(@class,'map-popup__submit')]").click();
        return new CheckoutPage();
    }
}

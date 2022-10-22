package com.softserveinc.ita.rozetka.components.profile;

import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$x;
import static com.softserveinc.ita.rozetka.utils.WebElementUtil.getText;
import static com.softserveinc.ita.rozetka.utils.WebElementUtil.isVisible;

public class MyOrderRecipientsSection {

    private final String recipientNameXpath = "//p[@class='recipient__name']";

    @Step("My order recipients section: start editing")
    public EditOrderRecipientSection startEditing() {
        $x("//ul[contains(@class,'recipient-list')]/following-sibling::div//button").click();
        return new EditOrderRecipientSection();
    }

    public String getRecipientName() {
        return getText(recipientNameXpath);
    }

    public String getRecipientPhone() {
        return getText("//p[@class='recipient__phone']");
    }

    public boolean isRecipientNameDisplayed() {
        return isVisible(recipientNameXpath);
    }
}
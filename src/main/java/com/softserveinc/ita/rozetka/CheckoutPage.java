package com.softserveinc.ita.rozetka;

import com.softserveinc.ita.rozetka.components.ContactInformationSection;
import com.softserveinc.ita.rozetka.components.OrderSection;

import com.softserveinc.ita.rozetka.components.Header;
import com.softserveinc.ita.rozetka.data.DeliveryTypes;
import com.softserveinc.ita.rozetka.modals.ChangeCityModal;
import com.softserveinc.ita.rozetka.modals.PickupPointModal;

import static com.codeborne.selenide.Selenide.$x;
import static com.softserveinc.ita.rozetka.utils.WebElementUtil.getText;
import static com.softserveinc.ita.rozetka.utils.WebElementUtil.isVisible;

public class CheckoutPage extends BasePage {

    public boolean isHeaderVisible() {
        return isVisible("//h1");
    }

    public boolean isTotalModalVisible() {
        return isVisible("//div[@class='checkout-total']");
    }

    public boolean isContactsModalVisible() {
        return isVisible("//rz-checkout-contact-info");
    }

    public boolean isOrderModalVisible() {
        return isVisible("//rz-checkout-order");
    }

    public long getTotalSum() {
        return Long.parseLong(getText("//dl[contains(@class, 'js-total')]/dd").replaceAll("\\D", ""));
    }

    public ContactInformationSection getContactInformationSection() {
        return new ContactInformationSection();
    }

    public OrderSection getOrderSection() {
        return new OrderSection();
    }

    public String getDeliveryTitle(int orderNumber) {
      return $x(String.format("(//div[@class='checkout-variant__content ng-star-inserted']/../../div//label)[%d]",
              orderNumber)).text();
    }
}
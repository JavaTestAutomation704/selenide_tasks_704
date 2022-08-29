package com.softserveinc.ita.rozetka;

import static utils.WebElementUtil.getText;
import static utils.WebElementUtil.isVisible;

public class CheckoutPage extends BasePage {

    public boolean isHeaderVisible() {
        return isVisible("//h1");
    }

    public boolean isTotalComponentVisible() {
        return isVisible("//div[@class='checkout-total']");
    }

    public boolean isContactsComponentVisible() {
        return isVisible("//rz-checkout-contact-info");
    }

    public boolean isOrderComponentVisible() {
        return isVisible("//rz-checkout-order");
    }

    public long getTotalSum() {
        return Long.parseLong(getText("//dl[contains(@class, 'js-total')]/dd").replaceAll("\\D", ""));
    }
}
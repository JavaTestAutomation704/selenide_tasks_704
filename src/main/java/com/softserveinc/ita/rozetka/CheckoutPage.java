package com.softserveinc.ita.rozetka;

import com.softserveinc.ita.rozetka.components.ContactInformationSection;
import com.softserveinc.ita.rozetka.components.OrderSection;
import com.softserveinc.ita.rozetka.components.PromoCodeSection;
import com.softserveinc.ita.rozetka.components.TotalOrderSection;
import lombok.Getter;

import static com.codeborne.selenide.Selenide.$x;
import static com.softserveinc.ita.rozetka.utils.WebElementUtil.getText;
import static com.softserveinc.ita.rozetka.utils.WebElementUtil.isVisible;
import static java.lang.String.format;

@Getter
public class CheckoutPage extends BasePage {

    private final ContactInformationSection contactInformationSection = new ContactInformationSection();
    private final PromoCodeSection promoCodeSection = new PromoCodeSection();
    private final TotalOrderSection totalOrderSection = new TotalOrderSection();

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

    public OrderSection getOrderSection(int orderNumber) {
        return new OrderSection(orderNumber);
    }

    public String getSelectedDeliveryTitle(int orderNumber) {
        return $x(format("(//div[@class='checkout-variant__content ng-star-inserted']/../../div//label)[%d]",
                orderNumber)).text();
    }
}
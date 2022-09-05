package com.softserveinc.ita.rozetka;

import com.softserveinc.ita.rozetka.components.Header;
import com.softserveinc.ita.rozetka.data.DeliveryTypes;
import com.softserveinc.ita.rozetka.modals.ChangeCityModal;
import com.softserveinc.ita.rozetka.modals.PickupPointModal;

import static com.codeborne.selenide.Selenide.$x;
import static com.softserveinc.ita.rozetka.utils.WebElementUtil.getText;
import static com.softserveinc.ita.rozetka.utils.WebElementUtil.isVisible;

public class CheckoutPage extends BasePage {
    private final String spanXpathTemplateDelivery = "//span[contains(text(),'%s')]";
    private final String xpathPickupPoint = "//button[contains(@class,'dropdown-button')]";

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

    public Header changeCity(String city) {
        $x("//span[@class='deliveries__city-title']").click();
        return new ChangeCityModal().changeCity(city);
    }

    public CheckoutPage selectDeliveryType(DeliveryTypes deliveryType) {
        $x(String.format(spanXpathTemplateDelivery, deliveryType.getDeliveryNameInUa())).click();
        return this;
    }

    public boolean isDeliverySelected(DeliveryTypes deliveryType) {
        return isVisible(String.format(spanXpathTemplateDelivery + "/../../../following-sibling::div", deliveryType));
    }

    public CheckoutPage selectPickupPoint(int number) {
        $x(xpathPickupPoint).click();
        $x(String.format("(//ul[contains(@class,'list-inner')]/li)[%d]", number)).click();
        return this;
    }

    public String getPickupPointName() {
        return $x(xpathPickupPoint).text();
    }

    public PickupPointModal openPickupPointModal() {
        return null;
    }
}
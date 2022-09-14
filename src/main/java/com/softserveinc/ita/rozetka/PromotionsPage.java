package com.softserveinc.ita.rozetka;

import com.softserveinc.ita.rozetka.components.Promotion;

import static com.softserveinc.ita.rozetka.utils.WebElementUtil.*;

public class PromotionsPage {

    public boolean isOpened() {
        return isVisible("//ul[contains(@class, 'promo-grid')]");
    }

    public Promotion getPromotion(int number) {
        return new Promotion(number);
    }

    public int getPromotionsQuantity() {
        var promotionXpath = "//rz-promotion-tile";
        waitTillVisible(promotionXpath);
        return getCollectionSize(promotionXpath);
    }
}
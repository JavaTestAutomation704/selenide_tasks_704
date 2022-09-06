package com.softserveinc.ita.rozetka;

import com.softserveinc.ita.rozetka.components.Promotion;

import static com.softserveinc.ita.rozetka.utils.WebElementUtil.getCollectionSize;
import static com.softserveinc.ita.rozetka.utils.WebElementUtil.isVisible;

public class PromotionsPage {

    public boolean isOpen() {
        var isTitleVisible = isVisible("//h1[contains(@class, 'heading')]");
        var isPromotionCardsVisible = isVisible("//ul[contains(@class, 'promo-grid')]");
        return isTitleVisible && isPromotionCardsVisible;
    }

    public Promotion getPromotion(int number) {
        return new Promotion(number);
    }

    public int getPromotionsQuantity() {
        return getCollectionSize("//rz-promotion-tile");
    }
}
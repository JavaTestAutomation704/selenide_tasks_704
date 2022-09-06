package com.softserveinc.ita.rozetka;

import com.softserveinc.ita.rozetka.components.Product;

import static com.softserveinc.ita.rozetka.utils.WebElementUtil.*;

public class PromotionPage {

    public boolean isOpen() {
        var isTitleVisible = isVisible("//h1[contains(@class, 'heading')]");
        var isPromotionCardsVisible = isVisible("//ul[contains(@class, 'catalog-grid')]");
        return isTitleVisible && isPromotionCardsVisible;
    }

    public int getProductsQuantity() {
        return getCollectionSize("//rz-catalog-tile");
    }

    public String getTitle() {
        return getText("//h1[contains(@class, 'heading')]//span");
    }

    public String getPromotionPeriod() {
        return getText("(//div[contains(@class,'promotion__description')]//div)[2]");
    }

    public Product getProduct(int number) {
        return new Product(number);
    }
}
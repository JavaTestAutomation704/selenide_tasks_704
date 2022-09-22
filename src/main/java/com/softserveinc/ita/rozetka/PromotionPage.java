package com.softserveinc.ita.rozetka;

import com.softserveinc.ita.rozetka.components.Product;

import static com.softserveinc.ita.rozetka.utils.WebElementUtil.*;

public class PromotionPage {

    public boolean isOpened() {
        return isVisible("//ul[contains(@class, 'catalog-grid')]");
    }

    public int getProductsQuantity() {
        var productXpath = "//rz-catalog-tile";
        waitTillVisible(productXpath);
        return getCollectionSize(productXpath);
    }

    public String getTitle() {
        return getText("//h1[contains(@class, 'heading')]//span");
    }

    public String getPromotionPeriod() {
        return getText("//time");
    }

    public Product getProduct(int number) {
        return new Product(number);
    }
}
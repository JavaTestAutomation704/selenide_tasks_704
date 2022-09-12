package com.softserveinc.ita.rozetka;

import com.softserveinc.ita.rozetka.components.Product;

import static com.softserveinc.ita.rozetka.utils.WebElementUtil.getCollectionSize;
import static com.softserveinc.ita.rozetka.utils.WebElementUtil.getText;

public class PromoPage {
    public String getTitle() {
        return getText("//h2[1]");
    }

    public Product getProduct(int number) {
        return new Product(number);
    }

    public int getProductsQuantity() {
        return getCollectionSize("//rz-catalog-tile");
    }
}

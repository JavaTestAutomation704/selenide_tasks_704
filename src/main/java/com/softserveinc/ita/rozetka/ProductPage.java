package com.softserveinc.ita.rozetka;

import com.softserveinc.ita.rozetka.modals.CreditModal;
import com.softserveinc.ita.rozetka.modals.ShoppingCartModal;

import static com.codeborne.selenide.Selenide.$x;

public class ProductPage extends BasePage {
    public String getTitle() {
        return $x("//h1[@class='product__title']").text();
    }

    public ShoppingCartModal addToCart() {
        $x("//ul[@class='product-buttons']//button[contains(@class,'buy-button')]").click();
        return new ShoppingCartModal();
    }

    public ProductCharacteristicsPage openCharacteristicsPage() {
        $x("//ul[@class='tabs__list']//a[contains(@href, 'characteristics')]").click();
        return new ProductCharacteristicsPage();
    }
    
    public CreditModal startPurchaseOnCredit() {
        return new CreditModal().open();
    }
}
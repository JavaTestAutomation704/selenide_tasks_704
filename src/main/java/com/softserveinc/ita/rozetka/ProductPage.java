package com.softserveinc.ita.rozetka;

import com.softserveinc.ita.rozetka.modals.CreditModal;
import com.softserveinc.ita.rozetka.modals.ShoppingCartModal;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$x;
import static com.softserveinc.ita.rozetka.utils.WebElementUtil.getLong;
import static com.softserveinc.ita.rozetka.utils.WebElementUtil.getText;

public class ProductPage extends BasePage {
    public String getTitle() {
        return getText("//h1[@class='product__title']").toLowerCase();
    }

    public long getPrice() {
        return getLong("//p[contains(@class, 'product-prices__big')]");
    }

    public ShoppingCartModal addToCart() {
        $x("//rz-product-tab-main//button[contains(@class,'buy-button')]").click();
        return new ShoppingCartModal();
    }

    public ProductCharacteristicsPage openCharacteristicsPage() {
        $x("//ul[@class='tabs__list']//a[contains(@href, 'characteristics')]").click();
        $x("//rz-product-tab-main").shouldNotBe(visible);
        return new ProductCharacteristicsPage();
    }

    @Step("Credit modal: start purchase on credit")
    public CreditModal startPurchaseOnCredit() {
        return new CreditModal().open();
    }
}
package com.softserveinc.ita.rozetka;

import com.softserveinc.ita.rozetka.modals.ChangeCityModal;
import com.softserveinc.ita.rozetka.modals.CreditModal;
import com.softserveinc.ita.rozetka.modals.ShoppingCartModal;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$x;
import static com.softserveinc.ita.rozetka.utils.WebElementUtil.*;

public class ProductPage extends BasePage {
    private final String titleXpath = "//h1[@class='product__title']";

    public String getTitle() {
        return getText(titleXpath).toLowerCase();
    }

    public long getPrice() {
        return getLong("//p[contains(@class, 'product-prices__big')]");
    }

    @Step("Shopping cart modal: add product to cart")
    public ShoppingCartModal addToCart() {
        $x("//rz-product-tab-main//button[contains(@class,'buy-button')]").click();
        return new ShoppingCartModal();
    }

    @Step("Product characteristics page: open product characteristics page")
    public ProductCharacteristicsPage openCharacteristicsPage() {
        $x("//ul[@class='tabs__list']//a[contains(@href, 'characteristics')]").click();
        $x("//rz-product-tab-main").shouldNotBe(visible);
        return new ProductCharacteristicsPage();
    }

    @Step("Product page: start purchase on credit")
    public CreditModal startPurchaseOnCredit() {
        $x(titleXpath).hover();
        return new CreditModal().open();
    }
    @Step("Product page: change city to {city}")
    public ProductPage changeCity(String city) {
        $x("//div[@class='product-about__block-heading']//button").click();
        new ChangeCityModal().changeCity(city);
        return this;
    }

    public boolean isBonusIconVisible() {
        return isVisible("//div[contains(@class, 'bonuses__icons')]");
    }

    public String getBonusText() {
        String xpathBonusText = "//div[contains(@class,'bonuses__info')]";
        if (isVisible(xpathBonusText)) {
            return $x(xpathBonusText).text();
        }
        return "";
    }
}
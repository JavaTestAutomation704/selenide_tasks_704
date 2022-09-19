package com.softserveinc.ita.rozetka;

import com.softserveinc.ita.rozetka.modals.ChangeCityModal;
import com.softserveinc.ita.rozetka.modals.CreditModal;
import com.softserveinc.ita.rozetka.modals.PromotionTermsModal;
import com.softserveinc.ita.rozetka.modals.ShoppingCartModal;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$x;
import static com.softserveinc.ita.rozetka.utils.WebElementUtil.*;
import static java.lang.String.format;

public class ProductPage extends BasePage {
    private final String titleXpath = "//h1[@class='product__title']";

    public String getTitle() {
        return getText(titleXpath).toLowerCase();
    }

    public long getPrice() {
        return getNumber("//p[contains(@class, 'product-prices__big')]");
    }

    @Step("Product page: add product to cart")
    public ShoppingCartModal addToCart() {
        $x("//rz-product-tab-main//button[contains(@class,'buy-button')]").click();
        return new ShoppingCartModal();
    }

    private void openPage(String pageName) {
        $x(format("//ul[@class='tabs__list']//a[contains(@href, '%s')]", pageName)).click();
        $x("//rz-product-tab-main").shouldNotBe(visible);
    }

    @Step("Product page: open product characteristics page")
    public ProductCharacteristicsPage openCharacteristicsPage() {
        openPage("characteristics");
        return new ProductCharacteristicsPage();
    }

    @Step("Product page: open product review page")
    public ProductReviewPage openReviewPage() {
        openPage("comments");
        return new ProductReviewPage();
    }

    @Step("Product page: start purchase on credit")
    public CreditModal startPurchaseOnCredit() {
        $x(titleXpath).hover();
        return new CreditModal().open();
    }

    @Step("Product page: open product questions page")
    public ProductQuestionsPage openProductQuestionsPage() {
        openPage("questions");
        return new ProductQuestionsPage();
    }

    public boolean isBuyOnCreditButtonVisible() {
        return isVisible("(//ul[@class='product-buttons']//li)[2]");
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
        var xpathBonusText = "//div[contains(@class,'bonuses__info')]";
        if (isVisible(xpathBonusText)) {
            return $x(xpathBonusText).text();
        }
        return "";
    }

    @Step("Product page: open promotion terms page modal")
    public PromotionTermsModal openPromotionTermsModal() {
        var promotionTermsModalButtonXpath = "//button[contains(@class, 'promotion')]";
        waitTillVisible(promotionTermsModalButtonXpath);
        $x(promotionTermsModalButtonXpath).click();
        return new PromotionTermsModal();
    }

    public boolean isOpenPromotionTermsModalButtonVisible() {
        return isVisible("//button[contains(@class, 'promotion')]");
    }

    public boolean isOpened() {
        return isVisible("(//div[@class = 'product-about'])[1]");
    }
}
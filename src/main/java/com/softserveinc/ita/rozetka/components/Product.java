package com.softserveinc.ita.rozetka.components;

import com.softserveinc.ita.rozetka.ProductPage;
import com.softserveinc.ita.rozetka.SearchResultsPage;
import com.softserveinc.ita.rozetka.data.Availability;
import io.qameta.allure.Step;

import static com.codeborne.selenide.ClickOptions.usingJavaScript;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$x;
import static com.softserveinc.ita.rozetka.utils.WebElementUtil.*;

public class Product {
    private final String productXpath;
    private final String titleXpath = "//span[@class='goods-tile__title']";

    public Product(int productNumber) {
        this.productXpath = String.format("(//rz-catalog-tile)[%d]", productNumber);
    }

    public Product(String productNumber) {
        if (productNumber.equals("last")) {
            this.productXpath = "(//rz-catalog-tile)[last()]";
        } else {
            this.productXpath = String.format("(//rz-catalog-tile)[%s]", productNumber);
        }
    }

    public Product(int productNumber, boolean isWithDiscount) {
        if (isWithDiscount) {
            this.productXpath = String.format("(//span[contains(@class, 'type_action')]/ancestor::rz-catalog-tile)[%d]",
                    productNumber);
        } else {
            this.productXpath = String.format("(//rz-catalog-tile)[%d]", productNumber);
        }
    }

    public String getTitle() {
        return getText(productXpath + titleXpath);
    }

    public String getTitleLowerCase() {
        return getTitle().toLowerCase();
    }

    public long getPrice() {
        return getNumber(productXpath + "//span[@class='goods-tile__price-value']");
    }

    @Step("Product: add product to shopping cart")
    public SearchResultsPage addToShoppingCart() {
        $x(productXpath + "//button[contains(@class, 'buy-button')]").click(usingJavaScript());
        return new SearchResultsPage();
    }

    @Step("Product: open product page")
    public ProductPage open() {
        $x(productXpath + titleXpath)
                .scrollIntoView(false)
                .click();
        $x("//h1[@class='product__title']")
                .shouldBe(visible)
                .hover();
        return new ProductPage();
    }

    public boolean isUsed() {
        return isVisible(productXpath + "//span[contains(@class, 'promo-label_type_used')]");
    }

    public Availability getAvailability() {
        String availability = getText(productXpath + "//div[contains(@class, 'availability')]");
        return Availability.getByValue(availability);
    }

    public boolean isAvailable() {
        Availability availability = getAvailability();
        return availability == Availability.AVAILABLE
                || availability == Availability.READY_TO_BE_DELIVERED
                || availability == Availability.RUNNING_OUT_OF_STOCK;
    }

    public boolean isOnSale() {
        return isVisible(productXpath + "//span[contains(@class, 'promo-label_type_popularity')]")
                || isVisible(productXpath + "//span[contains(@class, 'promo-label_type_action')]")
                || !$x(productXpath + "//div[contains(@class, 'price--old')]").text().equals("");
    }

    public boolean isInShoppingCart() {
        return isVisible(productXpath + "//button[contains(@class, 'buy-button_state_in-cart')]");
    }

    public boolean isLastSeen(String name) {
        return isVisible(String.format("(//section[contains(@class, 'main-goods')][1]//div[@class = 'tile'])[1]" +
                "//a[contains(text(), '%s')]", name));
    }

    public boolean isPreviouslySeen(String name) {
        return isVisible(String.format("(//section[contains(@class, 'main-goods')][1]//div[@class = 'tile'])[2]" +
                "//a[contains(text(), '%s')]", name));
    }

    @Step("Product: add product to comparison list")
    public SearchResultsPage addToComparisonList() {
        $x(productXpath + "//button[contains(@class, 'compare')]")
                .scrollIntoView(false)
                .click();
        return new SearchResultsPage();
    }

    public long getDiscount() {
        return getNumber(productXpath + "//span[contains(@class, 'type_action')]");
    }

    public long getOldPrice() {
        return getNumber(productXpath + "//div[contains(@class, 'old price')]");
    }
}
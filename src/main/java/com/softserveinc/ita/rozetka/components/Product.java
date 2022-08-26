package com.softserveinc.ita.rozetka.components;

import com.softserveinc.ita.rozetka.ProductPage;
import com.softserveinc.ita.rozetka.SearchResultsPage;
import com.softserveinc.ita.rozetka.data.Availability;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$x;
import static utils.WebElementUtil.*;

public class Product {
    private final String productNumber;
    private final String titleXpath = "(//span[@class='goods-tile__title'])[%d]";

    public Product(int productNumber) {
        this.productNumber = Integer.toString(productNumber);
    }

    public Product(String product) {
        if (product.equals("last")) {
            this.productNumber = "last()";
        } else {
            this.productNumber = product;
        }
    }

    public String getTitle() {
        return getText(String.format(titleXpath, productNumber)).toLowerCase();
    }

    public long getPrice() {
        return getLong(String.format("(//span[@class='goods-tile__price-value'])[%s]", productNumber));
    }

    public SearchResultsPage addToShoppingCart() {
        $x(String.format("(//button[contains(@class, 'buy-button')])[%s]", productNumber)).click();
        return new SearchResultsPage();
    }

    public ProductPage open() {
        $x(String.format(titleXpath, productNumber)).click();
        $x("//h1[@class='product__title']")
                .shouldBe(visible)
                .hover();
        return new ProductPage();
    }

    public String getStatus() {
        return getText(String.format("(//div[contains(@class, 'goods-tile__availability')])[%s]", productNumber));
    }

    public boolean isUsed() {
        return isVisible(String.format("(//span[contains(@class, 'promo-label_type_used')])[%s]", productNumber));
    }

    public boolean isAvailable() {
        return Availability.getByValue(this.getStatus()) != null;
    }
}
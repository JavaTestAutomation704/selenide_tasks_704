package com.softserveinc.ita.rozetka.components;

import com.softserveinc.ita.rozetka.ProductPage;
import com.softserveinc.ita.rozetka.SearchResultsPage;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$x;
import static utils.WebElementUtil.*;

public class Product {
    private final String productNumber;
    private final String titleXpath = "(//span[@class='goods-tile__title'])[%s]";

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

    public String getAvailability() {
        return getText(String.format("(//div[contains(@class, 'goods-tile__availability')])[%s]", productNumber));
    }

    public boolean isUsed() {
        return isVisible(String.format("(//span[contains(@class, 'promo-label_type_used')])[%s]", productNumber));
    }
}
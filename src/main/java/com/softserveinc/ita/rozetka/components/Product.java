package com.softserveinc.ita.rozetka.components;

import com.softserveinc.ita.rozetka.ProductPage;
import com.softserveinc.ita.rozetka.SearchResultsPage;
import lombok.RequiredArgsConstructor;

import static com.codeborne.selenide.Selenide.$x;
import static utils.WebElementUtil.getText;
import static utils.WebElementUtil.isVisible;

@RequiredArgsConstructor
public class Product {
    private final int productNumber;

    public String getTitle() {
        return getText(String.format("(//span[@class='goods-tile__title'])[%d]", productNumber)).toLowerCase();
    }

    public long getPrice() {
        return Long.parseLong(getText(String.format("(//span[@class='goods-tile__price-value'])[%d]", productNumber)));
    }

    public SearchResultsPage addToShoppingCart() {
        $x(String.format("(//button[contains(@class, 'buy-button')])[%d]", productNumber)).click();
        return new SearchResultsPage();
    }

    public ProductPage open() {
        $x(String.format("(//a[contains(@class, 'goods-tile__picture')])[%d]", productNumber)).click();
        return new ProductPage();
    }

    public String getAvailability() {
        return getText(String.format("(//div[contains(@class, 'goods-tile__availability')])[%d]", productNumber));
    }

    public boolean isUsed() {
        return isVisible(String.format("(//span[contains(@class, 'promo-label_type_used')])[%d]", productNumber));
    }
}

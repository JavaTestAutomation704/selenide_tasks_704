package com.softserveinc.ita.rozetka.components;

import com.softserveinc.ita.rozetka.ProductPage;
import com.softserveinc.ita.rozetka.SearchResultsPage;
import lombok.RequiredArgsConstructor;

import static com.codeborne.selenide.Selenide.$x;
import static utils.WebElementUtil.getText;
import static utils.WebElementUtil.isVisible;

@RequiredArgsConstructor
public class Product {
    private final int id;
    private String titleXpath = "(//span[@class='goods-tile__title'])[%d]";

    public String getTitle() {
        return getText(String.format(titleXpath, id)).toLowerCase();
    }

    public long getPrice() {
        return Long.parseLong(getText(String.format("(//span[@class='goods-tile__price-value'])[%d]", id)));
    }

    public SearchResultsPage addToCartList() {
        $x(String.format("(//button[contains(@class, 'buy-button')])[%d]", id)).click();
        return new SearchResultsPage();
    }

    public ProductPage open() {
        $x(String.format(titleXpath, id)).click();
        return new ProductPage();
    }

    public String getAvailability() {
        return getText(String.format("(//div[contains(@class, 'goods-tile__availability')])[%d]", id));
    }

    public boolean isUsed() {
        return isVisible(String.format("(//span[contains(@class, 'promo-label_type_used')])[%d]", id));
    }
}

package com.softserveinc.ita.rozetka;

import static com.codeborne.selenide.Selenide.$;

public class ProductPage extends BasePage {
    public String getPrice() {
        return $("//div[@class='product-prices__inner ng-star-inserted']").text();
    }
}
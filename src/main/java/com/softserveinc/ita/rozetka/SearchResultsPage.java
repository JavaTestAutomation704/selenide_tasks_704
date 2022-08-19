package com.softserveinc.ita.rozetka;

import com.softserveinc.ita.rozetka.components.ResultsFilter;

import static com.codeborne.selenide.Selenide.$$x;


public class SearchResultsPage extends BasePage {
    public ResultsFilter getFilter() {
        return new ResultsFilter();
    }

    public boolean isProductsOnSale() {
        int countProducts = $$x("//div[contains(@class, 'goods-tile__inner')]").size();
        int popularityProducts = $$x("//span[contains(@class, 'promo-label_type_popularity')]").size();
        int actionProducts = $$x("//span[contains(@class, 'promo-label_type_action')]").size();

        return countProducts == popularityProducts + actionProducts;

    }
}
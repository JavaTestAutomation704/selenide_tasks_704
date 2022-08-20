package com.softserveinc.ita.rozetka;

import com.softserveinc.ita.rozetka.components.ResultsFilter;

import java.util.ArrayList;
import java.util.List;

import static com.codeborne.selenide.Selenide.$x;

public class SearchResultsPage extends BasePage {

    public ResultsFilter getFilter() {
        return new ResultsFilter();
    }

    public SearchResultsPage addToCart(int number) {
        $x(String.format("(//button[contains(@class, 'buy-button')])[%d]", number)).click();
        return this;
    }

    public List<String> getProductStatuses() {
        List<String> productStatuses = new ArrayList<>();
        productStatuses.add($x("//div[contains(@class, 'goods-tile__availability')]").text());
        return productStatuses;
    }

    public List<String> getProductTitles() {
        List<String> productTitles = new ArrayList<>();
        productTitles.add($x("//span[contains(@class, 'goods-tile__title')]").text());
        return productTitles;
    }
}
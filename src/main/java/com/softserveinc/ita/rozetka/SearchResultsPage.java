package com.softserveinc.ita.rozetka;

import com.softserveinc.ita.rozetka.components.Product;
import com.softserveinc.ita.rozetka.components.ResultsFilter;
import com.softserveinc.ita.rozetka.data.ProductSort;
import utils.WebElementUtil;

import java.util.ArrayList;
import java.util.List;

import static com.codeborne.selenide.Selenide.$x;

public class SearchResultsPage extends BasePage {
    private final String productXpath = "(//div[contains(@class, 'goods-tile ')])[%d]";

    public ResultsFilter getFilter() {
        return new ResultsFilter();
    }

    public ProductPage openProductPage(int number) {
        return new Product(number).open();
    }

    public int getResultsAmount() {
        return Integer.parseInt(
                $x("//p[contains(@class, 'selection')]")
                        .getText()
                        .replaceAll("[^0-9]", ""));
    }

    public SearchResultsPage resetFilters() {
        $x("//button[contains(@class, 'reset')]").click();
        return this;
    }
    
    public Product get(int productNumber) {
        return new Product(productNumber);
    }

    public SearchResultsPage sortBy(ProductSort sort) {
        $x("//rz-sort//select").click();
        $x(String.format("//rz-sort//select//option[contains(@value, '%s')]", sort.getOptionXpath())).click();
        return this;
    }
}
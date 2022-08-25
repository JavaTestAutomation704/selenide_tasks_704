package com.softserveinc.ita.rozetka;

import com.softserveinc.ita.rozetka.components.Product;
import com.softserveinc.ita.rozetka.components.ResultsFilter;
import com.softserveinc.ita.rozetka.data.ProductSort;
import utils.WebElementUtil;

import java.util.ArrayList;
import java.util.List;

import static com.codeborne.selenide.Selenide.$x;
import static utils.WebElementUtil.isVisible;

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

    public SearchResultsPage addToCart(int productNumber) {
        $x(String.format("(//button[contains(@class, 'buy-button')])[%d]", productNumber)).click();
        return this;
    }

    public String getAvailability(int productNumber) {
        return WebElementUtil
                .getText(String.format("(//div[contains(@class, 'goods-tile__availability')])[%d]", productNumber))
                .toLowerCase();
    }

    public List<String> getProductsTitles(int amount) {
        List<String> productData = new ArrayList<>();
        for (int i = 0; i < amount; i++) {
            productData.add(WebElementUtil
                    .getText(String.format("(//span[contains(@class, 'goods-tile__title')])[%d]", i))
                    .toLowerCase());
        }
        return productData;
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
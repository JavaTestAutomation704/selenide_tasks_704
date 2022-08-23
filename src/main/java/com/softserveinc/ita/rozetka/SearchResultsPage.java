package com.softserveinc.ita.rozetka;

import com.softserveinc.ita.rozetka.components.ResultsFilter;
import utils.WebElementUtil;

import java.util.ArrayList;
import java.util.List;

import static com.codeborne.selenide.Selenide.$x;

public class SearchResultsPage extends BasePage {

    public ResultsFilter getFilter() {
        return new ResultsFilter();
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
}
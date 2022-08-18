package com.softserveinc.ita.rozetka;

import com.softserveinc.ita.rozetka.components.ResultsFilter;

import static com.codeborne.selenide.Selenide.$x;

public class SearchResultsPage extends BasePage {
    String productXpath = "(//div[contains(@class, 'goods-tile ')])[%d]";

    public ResultsFilter getFilter() {
        return new ResultsFilter();
    }

    public ProductPage open(int productNumber) {
        $x(String.format(productXpath, productNumber)).click();
        return new ProductPage();
    }

    public int getResultsAmount() {
        return Integer.parseInt(
                $x("//p[contains(@class, 'selection')]")
                        .getText()
                        .replaceAll("[^0-9]", ""));
    }

    public SearchResultsPage resetFilers() {
        $x("//button[contains(@class, 'reset')]").click();
        return this;
    }
}
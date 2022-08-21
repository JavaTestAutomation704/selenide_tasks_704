package com.softserveinc.ita.rozetka;

import com.softserveinc.ita.rozetka.components.ResultsFilter;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;

import static com.codeborne.selenide.Selenide.$x;

@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
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

    public SearchResultsPage resetFilters() {
        $x("//button[contains(@class, 'reset')]").click();
        return this;
    }
}
package com.softserveinc.ita.rozetka;

import com.softserveinc.ita.rozetka.components.ResultsFilter;

import static com.codeborne.selenide.Selenide.$x;

public class SearchResultsPage extends BasePage {
    public ResultsFilter getFilter() {
        return new ResultsFilter();
    }

    public String getTitle(int product) {
        return $x(String.format("(//span[@class='goods-tile__title'])[%d]", product)).text();
    }

    public long getPrice(int product) {
        return Long.parseLong($x(String.format("(//span[@class='goods-tile__price-value'])[%d]", product)).text());
    }

    public SearchResultsPage sortDescByPrice() {
        $x("//rz-sort//select").click();
        $x("//rz-sort//select//option[contains(@value, 'expensive')]").click();
        return this;
    }
}
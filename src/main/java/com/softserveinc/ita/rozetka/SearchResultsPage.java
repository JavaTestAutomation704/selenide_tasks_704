package com.softserveinc.ita.rozetka;

import com.softserveinc.ita.rozetka.components.ResultsFilter;

import static com.codeborne.selenide.Selenide.$x;

public class SearchResultsPage extends BasePage {
    public ResultsFilter getFilter() {
        return new ResultsFilter();
    }
    public SearchResultsPage sortAscendingByPrice() {
        $x("//rz-sort//select").click();
        $x("//rz-sort//select//option[contains(@value, 'cheap')]").click();
        return this;
    }
}
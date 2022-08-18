package com.softserveinc.ita.rozetka.components;

import com.softserveinc.ita.rozetka.SearchResultsPage;
import utils.ProductFilter;

import static com.codeborne.selenide.Selenide.$x;

public class ResultsFilter extends Header {
    public SearchResultsPage toggleFilter(ProductFilter... type) {
        for (ProductFilter filter : type) {
            $x(String.format("//a[@data-id = '%s']", filter.getFilterXpath())).click();
        }
        return new SearchResultsPage();
    }
}
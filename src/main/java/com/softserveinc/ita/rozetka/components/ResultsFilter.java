package com.softserveinc.ita.rozetka.components;

import com.softserveinc.ita.rozetka.SearchResultsPage;
import utils.ProductFilter;

import java.util.List;

import static com.codeborne.selenide.Selenide.$x;

public class ResultsFilter extends Header {

    public SearchResultsPage toggleFilter(ProductFilter type) {
        $x(String.format("//a[@data-id = '%s']", type.getFilterXpath())).click();
        return new SearchResultsPage();
    }

    public SearchResultsPage toggleFilter(List<ProductFilter> filters) {
        for (ProductFilter filter : filters) {
            $x(String.format("//a[@data-id = '%s']", filter.getFilterXpath())).click();
        }
        return new SearchResultsPage();
    }
}
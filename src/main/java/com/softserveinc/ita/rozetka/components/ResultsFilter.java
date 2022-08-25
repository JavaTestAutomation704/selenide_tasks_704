package com.softserveinc.ita.rozetka.components;

import com.softserveinc.ita.rozetka.SearchResultsPage;
import com.softserveinc.ita.rozetka.data.ProductFilter;

import java.util.List;

import static com.codeborne.selenide.Selenide.$x;
import static utils.WebElementUtil.isVisible;

public class ResultsFilter extends Header {

    public SearchResultsPage filter(ProductFilter type) {
        $x(String.format("//a[@data-id = '%s']", type.getFilterXpath())).click();
        isVisible(String.format("//a[@data-id = '%s'][contains(@class, 'link--checked')]", type.getFilterXpath()));
        return new SearchResultsPage();
    }

    public SearchResultsPage filter(List<ProductFilter> types) {
        types.forEach(filter -> $x(String.format("//a[@data-id = '%s']", filter.getFilterXpath())).click());
        return new SearchResultsPage();
    }
}
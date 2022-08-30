package com.softserveinc.ita.rozetka.components;

import com.softserveinc.ita.rozetka.SearchResultsPage;
import com.softserveinc.ita.rozetka.data.ProductFilter;
import com.softserveinc.ita.rozetka.utils.WebElementUtil;

import java.util.List;

import static com.codeborne.selenide.Selenide.$x;

public class Filter extends Header {

    public SearchResultsPage filter(ProductFilter type) {
        $x(String.format("//a[@data-id = '%s']", type.getFilterXpath()))
                .scrollIntoView(false)
                .click();
        WebElementUtil.waitTillVisible(String.format("//a[@data-id = '%s'][contains(@class, 'link--checked')]",
                type.getFilterXpath()));
        return new SearchResultsPage();
    }

    public SearchResultsPage filter(List<ProductFilter> types) {
        types.forEach(filter -> $x(String.format("//a[@data-id = '%s']", filter.getFilterXpath()))
                .scrollIntoView(false)
                .click());
        return new SearchResultsPage();
    }
}
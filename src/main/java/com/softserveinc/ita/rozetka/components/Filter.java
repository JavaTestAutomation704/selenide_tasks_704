package com.softserveinc.ita.rozetka.components;

import com.softserveinc.ita.rozetka.SearchResultsPage;
import com.softserveinc.ita.rozetka.data.PriceFilter;
import com.softserveinc.ita.rozetka.data.ProductFilter;
import com.softserveinc.ita.rozetka.utils.WebElementUtil;
import io.qameta.allure.Step;

import java.util.List;

import static com.codeborne.selenide.Selenide.$x;

public class Filter extends Header {

    @Step("Search results page: select filter {type}")
    public SearchResultsPage filter(ProductFilter type) {
        $x(String.format("//a[@data-id = '%s']", type.getFilterXpath()))
                .scrollIntoView(false)
                .click();
        WebElementUtil.waitTillVisible(String.format("//a[@data-id = '%s'][contains(@class, 'link--checked')]",
                type.getFilterXpath()));
        return new SearchResultsPage();
    }

    @Step("Search results page: select filters {types}")
    public SearchResultsPage filter(List<ProductFilter> types) {
        types.forEach(filter -> $x(String.format("//a[@data-id = '%s']", filter.getFilterXpath()))
                .scrollIntoView(false)
                .click());
        return new SearchResultsPage();
    }

    public PriceFilter getPriceFilter() {
        return new PriceFilter();
    }
}
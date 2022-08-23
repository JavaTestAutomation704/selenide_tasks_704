package com.softserveinc.ita.rozetka;

import com.softserveinc.ita.rozetka.components.Product;
import com.softserveinc.ita.rozetka.components.ResultsFilter;
import com.softserveinc.ita.rozetka.data.ProductSort;

import static com.codeborne.selenide.Selenide.$x;

public class SearchResultsPage extends BasePage {
    public ResultsFilter getFilter() {
        return new ResultsFilter();
    }

    public Product get(int productNumber) {
        return new Product(productNumber);
    }
    
    public SearchResultsPage sortBy(ProductSort sort) {
        $x("//rz-sort//select").click();
        $x(String.format("//rz-sort//select//option[contains(@value, '%s')]", sort.getOptionXpath())).click();
        return this;
    }
}
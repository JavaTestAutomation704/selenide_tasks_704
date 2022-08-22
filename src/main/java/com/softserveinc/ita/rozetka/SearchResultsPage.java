package com.softserveinc.ita.rozetka;

import com.softserveinc.ita.rozetka.components.ResultsFilter;

import java.util.ArrayList;
import java.util.List;

import static utils.WebElementUtil.getCollectionSize;
import static utils.WebElementUtil.getText;


public class SearchResultsPage extends BasePage {
    public ResultsFilter getFilter() {
        return new ResultsFilter();
    }

    public List<String> getProductStates() {
        String productsStateXpath = "//span[contains(@class, 'promo-label_type_used')]";
        List<String> states = new ArrayList<>();
        for (int i = 0; i < getCollectionSize(productsStateXpath); i++) {
            states.add(getText(String.format("([%s])[%s]", productsStateXpath, i)).toLowerCase());
        }
        return states;
    }
}
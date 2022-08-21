package com.softserveinc.ita.rozetka;

import com.softserveinc.ita.rozetka.components.ResultsFilter;

public class SearchResultsPage extends BasePage {
    public ResultsFilter getFilter() {
        return new ResultsFilter();
    }
}
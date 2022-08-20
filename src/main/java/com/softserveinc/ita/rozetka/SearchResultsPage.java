package com.softserveinc.ita.rozetka;

import com.softserveinc.ita.rozetka.components.ResultsFilter;
import utils.WebElementUtil;

import java.util.ArrayList;
import java.util.List;

import static com.codeborne.selenide.Selenide.$x;

public class SearchResultsPage extends BasePage {

    public ResultsFilter getFilter() {
        return new ResultsFilter();
    }

    public SearchResultsPage addToCart(int number) {
        $x(String.format("(//button[contains(@class, 'buy-button')])[%d]", number)).click();
        return this;
    }

    public List<String> getFirstProductTitles(int amount) {
        return WebElementUtil.getTextList(amount,"//span[contains(@class, 'goods-tile__title')]");
    }

    public List<String> getProductStatuses() {
        List<String> titles = new ArrayList<>();
        titles.add(WebElementUtil.getText("//div[contains(@class, 'goods-tile__availability')]"));
        return titles;
    }
}
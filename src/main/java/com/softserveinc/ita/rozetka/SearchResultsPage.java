package com.softserveinc.ita.rozetka;

import com.softserveinc.ita.rozetka.components.Product;
import com.softserveinc.ita.rozetka.components.Filter;
import com.softserveinc.ita.rozetka.data.ProductSort;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$x;
import static utils.WebElementUtil.*;

public class SearchResultsPage extends BasePage {

    public Filter getFilter() {
        return new Filter();
    }

    public ProductPage openProductPage(int number) {
        return new Product(number).open();
    }

    public int getResultsAmount() {
        return Integer.parseInt(
                $x("//p[contains(@class, 'selection')]")
                        .getText()
                        .replaceAll("[^0-9]", ""));
    }

    public int getProductAmount() {
        return getCollectionSize("(//rz-catalog-tile)");
    }

    public SearchResultsPage resetFilters() {
        $x("//button[contains(@class, 'reset')]").click();
        return this;
    }

    public Product getProduct(int number) {
        return new Product(number);
    }

    public Product getProduct(String number) {
        return new Product(number);
    }

    public SearchResultsPage sortBy(ProductSort sort) {
        String firstResultXpath = "(//div[contains(@class, 'goods-tile ')])[1]";
        String firstResultText = getText(firstResultXpath);
        $x("//rz-sort//select").click();
        $x(String.format("//rz-sort//select//option[contains(@value, '%s')]", sort.getOptionXpath())).click();
        $x(firstResultXpath).shouldNotHave(text(firstResultText));
        return this;
    }
}
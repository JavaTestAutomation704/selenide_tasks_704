package com.softserveinc.ita.rozetka;

import com.softserveinc.ita.rozetka.components.Filter;
import com.softserveinc.ita.rozetka.components.Product;
import com.softserveinc.ita.rozetka.data.ProductSort;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$x;
import static com.softserveinc.ita.rozetka.utils.WebElementUtil.*;


public class SearchResultsPage extends BasePage {

    public Filter getFilter() {
        return new Filter();
    }

    public int getResultsAmount() {
        String resultsAmountXpath = "//p[contains(@class, 'selection')]";
        waitTillVisible(resultsAmountXpath);
        return Integer.parseInt(
                $x(resultsAmountXpath)
                        .getText()
                        .replaceAll("[^0-9]", ""));
    }

    @Step("Search results page: reset filters")
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

    public Product getProductWithDiscount(int number) {
        return new Product(number, "type_action");
    }

    @Step("Search results page: sort search results by {sort}")
    public SearchResultsPage sortBy(ProductSort sort) {
        $x("//rz-sort//select").click();
        $x(String.format("//rz-sort//select//option[contains(@value, '%s')]", sort.getOptionXpath())).click();
        waitUntilUrlContains(String.format("sort=%s", sort.getOptionXpath()));
        return this;
    }

    public int getProductsQuantity() {
        return getCollectionSize("//rz-catalog-tile");
    }

    public int getProductsWithDiscountQuantity() {
        return getCollectionSize("//rz-catalog-tile//span[contains(@class, 'label_type_action')]");
    }
    
    public boolean doesTitleContain(String keyword) {
        return isVisible(String.format("//h1[contains(text(), '%s')]", keyword));
    }
}
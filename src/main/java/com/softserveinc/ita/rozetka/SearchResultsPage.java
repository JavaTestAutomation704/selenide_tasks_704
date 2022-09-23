package com.softserveinc.ita.rozetka;

import com.softserveinc.ita.rozetka.components.Filter;
import com.softserveinc.ita.rozetka.components.PagePagination;
import com.softserveinc.ita.rozetka.components.Product;
import com.softserveinc.ita.rozetka.data.ProductSort;
import com.softserveinc.ita.rozetka.modals.DrinkingAgeConfirmationModal;
import io.qameta.allure.Step;
import lombok.Getter;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$x;
import static com.codeborne.selenide.Selenide.actions;
import static com.softserveinc.ita.rozetka.utils.WebElementUtil.*;
import static java.lang.String.format;


public class SearchResultsPage extends BasePage {

    @Getter
    private final PagePagination pagePagination = new PagePagination();
    @Getter
    private final Filter filter = new Filter();
    @Getter
    private final DrinkingAgeConfirmationModal drinkingAgeConfirmationModal= new DrinkingAgeConfirmationModal();
    private final String resultsAmountXpath = "//p[contains(@class, 'selection')]";

    public long getResultsAmount() {
        waitTillVisible(resultsAmountXpath);
        return getNumber(resultsAmountXpath);
    }

    @Step("Search results page: reset filters")
    public SearchResultsPage resetFilters() {
        long resultsAmount = getResultsAmount();
        actions().click($x("//button[contains(@class, 'reset')]")).perform();
        waitForTextChange(resultsAmountXpath, String.valueOf(resultsAmount));
        return this;
    }

    public Product getProduct(int number) {
        return new Product(number);
    }

    public Product getProduct(String number) {
        return new Product(number);
    }

    public Product getProductWithDiscount(int number) {
        return new Product(number, true);
    }

    @Step("Search results page: sort search results by {sort}")
    public SearchResultsPage sortBy(ProductSort sort) {
        $x("//rz-sort//select").click();
        $x(format("//rz-sort//select//option[contains(@value, '%s')]", sort.getOptionXpath())).click();

        var preloaderXpath = "//main[contains(@class, 'preloader_type_element')]";
        if (isVisible(preloaderXpath, 10)) {
            $x(preloaderXpath).shouldNotBe(visible);
        }
        return this;
    }

    public int getProductsQuantity() {
        return getCollectionSize("//rz-catalog-tile");
    }

    public boolean doesTitleContain(String keyword) {
        return isVisible(format("//h1[contains(text(), '%s')]", keyword));
    }

    public int getProductsWithDiscountQuantity() {
        return getCollectionSize("//rz-catalog-tile//span[contains(@class, 'label_type_action')]");
    }
}
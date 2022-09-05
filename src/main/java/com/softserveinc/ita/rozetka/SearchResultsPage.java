package com.softserveinc.ita.rozetka;

import com.softserveinc.ita.rozetka.components.Filter;
import com.softserveinc.ita.rozetka.components.Product;
import com.softserveinc.ita.rozetka.data.ProductSort;
import com.softserveinc.ita.rozetka.modals.DrinkingAgeConfirmationModal;
import io.qameta.allure.Step;

import java.time.Duration;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$x;
import static com.softserveinc.ita.rozetka.utils.WebElementUtil.*;


public class SearchResultsPage extends BasePage {

    public Filter getFilter() {
        return new Filter();
    }

    public int getResultsAmount() {
        return Integer.parseInt(
                $x("//p[contains(@class, 'selection')]")
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

    @Step("Search results page: sort search results by {sort}")
    public SearchResultsPage sortBy(ProductSort sort) {
        $x("//rz-sort//select").click();
        $x(String.format("//rz-sort//select//option[contains(@value, '%s')]", sort.getOptionXpath())).click();

        var preloaderXpath = "//main[contains(@class, 'preloader_type_element')]";
        if (isVisible(preloaderXpath, 3)) {
            $x(preloaderXpath).shouldNotBe(visible, Duration.ofSeconds(3));
        }
        return this;
    }

    public int getProductsQuantity() {
        return getCollectionSize("//rz-catalog-tile");
    }

    public boolean doesTitleContain(String keyword) {
        return isVisible(String.format("//h1[contains(text(), '%s')]", keyword));
    }

    public DrinkingAgeConfirmationModal getDrinkingAgeConfirmationModal() {
        return new DrinkingAgeConfirmationModal();
    }
}
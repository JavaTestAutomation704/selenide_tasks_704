package com.softserveinc.ita.rozetka;

import com.softserveinc.ita.rozetka.components.Filter;
import com.softserveinc.ita.rozetka.components.Header;
import com.softserveinc.ita.rozetka.data.ProductFilter;
import com.softserveinc.ita.rozetka.data.ProductSort;
import org.assertj.core.api.SoftAssertions;
import com.softserveinc.ita.rozetka.data.Category;
import com.softserveinc.ita.rozetka.data.subcategory.page.LaptopsAndComputers;
import org.assertj.core.api.SoftAssertions;
import org.testng.annotations.Test;

import java.util.List;

import static com.softserveinc.ita.rozetka.data.ProductFilter.*;
import static org.assertj.core.api.Assertions.assertThat;

public class FilterProductTest extends TestRunner {
    @Test
    public void verifyUserCanSortProductsByPromotion() {
        Header header = homePage.getHeader();
        SearchResultsPage searchResultsPage = header
                .search("laptop")
                .sortBy(ProductSort.PROMOTION);

        SoftAssertions softAssertions = new SoftAssertions();
        softAssertions.assertThat(searchResultsPage.getProductsSize())
                .isGreaterThanOrEqualTo(60)
                .as("Products should be 60 or more");

        for (int productNumber : new int[]{1, 25, 59}) {
            softAssertions.assertThat(searchResultsPage.getProduct(productNumber).isOnSale())
                    .isTrue()
                    .as(String.format("Product with number %s should be on sale", productNumber));
        }

        searchResultsPage = searchResultsPage
                .sortBy(ProductSort.RATING)
                .getFilter()
                .filter(ProductFilter.PROMOTION);

        for (int productNumber : new int[]{2, 40, 60}) {
            softAssertions.assertThat(searchResultsPage.getProduct(productNumber).isOnSale())
                    .isTrue()
                    .as(String.format("Product with number %s should be on sale", productNumber));
        }

        softAssertions.assertAll();
    }


    @Test
    public void verifyProductAvailabilityFilter() {
        SearchResultsPage searchResultsPage = homePage
                .openCategoryPage(Category.LAPTOPS_AND_COMPUTERS)
                .openSubcategoryPage(LaptopsAndComputers.NOTEBOOKS)
                .getFilter()
                .filter(AVAILABLE);

        int productsQuantity = searchResultsPage.getProductsQuantity();
        int productsQuantityToCheck = 20;

        assertThat(productsQuantity)
                .as("Products quantity should be sufficient")
                .isGreaterThanOrEqualTo(productsQuantityToCheck);

        SoftAssertions softly = new SoftAssertions();

        for (int i = 1; i <= productsQuantityToCheck; i++) {
            softly.assertThat(searchResultsPage
                            .getProduct(i)
                            .isAvailable())
                    .as("Product should be available")
                    .isTrue();
        }
        softly.assertAll();
    }

    @Test
    public void verifyResettingFilters() {
        SearchResultsPage searchResultsPage = homePage
                .getHeader()
                .search("Xbox");

        int resultsAmountAfterSearch = searchResultsPage.getResultsAmount();

        Filter filter = searchResultsPage.getFilter();

        int resultsAmountAfterFilters = filter
                .filter(List.of(WHITE_COLOR, ROZETKA_SELLER, AVAILABLE))
                .getResultsAmount();

        searchResultsPage.resetFilters();
        filter.filter(MICROSOFT_BRAND);

        int resultsAmountAfterResetting = searchResultsPage.getResultsAmount();

        SoftAssertions softAssert = new SoftAssertions();

        softAssert
                .assertThat(resultsAmountAfterResetting)
                .as("Results amount after resetting should be grater than after filters")
                .isGreaterThan(resultsAmountAfterFilters);
        softAssert
                .assertThat(resultsAmountAfterResetting)
                .as("Results amount after resetting should be the same as after search")
                .isEqualTo(resultsAmountAfterSearch);

        softAssert.assertAll();
    }
}
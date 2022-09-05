package com.softserveinc.ita.rozetka;

import com.softserveinc.ita.rozetka.components.Filter;
import com.softserveinc.ita.rozetka.data.Category;
import com.softserveinc.ita.rozetka.data.ProductFilter;
import com.softserveinc.ita.rozetka.data.subcategory.LaptopsAndComputersSubcategory;
import com.softserveinc.ita.rozetka.utils.TestRunner;
import org.assertj.core.api.SoftAssertions;
import org.testng.annotations.Test;

import java.util.List;

import static com.softserveinc.ita.rozetka.data.Category.SMARTPHONES_TV_AND_ELECTRONICS;
import static com.softserveinc.ita.rozetka.data.ProductFilter.*;
import static com.softserveinc.ita.rozetka.data.subcategory.SmartphonesTvAndElectronicsSubcategory.MOBILE_PHONES;
import static org.assertj.core.api.Assertions.assertThat;

public class FilterProductTest extends TestRunner {
    @Test
    public void verifySaleFilterFunctionality() {
        var header = homePage.getHeader();
        var searchResultsPage = header.search("laptop");

        assertThat(searchResultsPage.getProductsQuantity())
                .as("Product quantity should be sufficient")
                .isGreaterThanOrEqualTo(60);

        searchResultsPage = searchResultsPage
                .getFilter()
                .filter(ProductFilter.PROMOTION);

        var softAssertions = new SoftAssertions();
        for (int productNumber : new int[]{2, 40, 60}) {
            var isProductOnSale = searchResultsPage
                    .getProduct(productNumber)
                    .isOnSale();

            // TODO: This test may be failed as product wasn't have sale label or old price
            softAssertions.assertThat(isProductOnSale)
                    .as(productNumber + " product should be on sale")
                    .isTrue();
        }

        softAssertions.assertAll();
    }

    @Test
    public void verifyProductAvailabilityFilter() {
        var filter = homePage
                .openCategoryPage(Category.LAPTOPS_AND_COMPUTERS)
                .openSubcategoryPage(LaptopsAndComputersSubcategory.NOTEBOOKS)
                .getFilter();

        var searchResultsPage = filter.filter(AVAILABLE);

        assertThat(filter.isSelected(AVAILABLE))
                .as("Filter should be selected")
                .isTrue();

        int productsQuantity = searchResultsPage.getProductsQuantity();
        int productsQuantityToCheck = 20;

        assertThat(productsQuantity)
                .as("Products quantity should be sufficient")
                .isGreaterThanOrEqualTo(productsQuantityToCheck);

        var softly = new SoftAssertions();

        for (int i = 1; i <= productsQuantityToCheck; i++) {
            //TODO: This test may be failed as unavailable products might be among the results
            softly.assertThat(searchResultsPage
                            .getProduct(i)
                            .isAvailable())
                    .as("Product should be available")
                    .isTrue();
        }
        softly.assertAll();
    }

    @Test
    public void verifyFilterByLoyaltyProgram() {
        Filter filter = homePage
                .openCategoryPage(SMARTPHONES_TV_AND_ELECTRONICS)
                .openSubcategoryPage(MOBILE_PHONES)
                .getFilter();
        filter.filter(AVAILABLE);
        SearchResultsPage searchResultsPage = filter.filter(WITH_BONUS);
        int productsQuantity = 5;

        assertThat(searchResultsPage.getProductsQuantity())
                .as("Products amount should be sufficient")
                .isGreaterThanOrEqualTo(productsQuantity);

        SoftAssertions softAssertions = new SoftAssertions();
        for (int i = 1; i <= productsQuantity; i++) {
            ProductPage productPage = searchResultsPage
                    .getProduct(i)
                    .open();

            softAssertions.assertThat(productPage.isBonusIconVisible())
                    .as("Bonus icon should be displayed")
                    .isTrue();
            softAssertions.assertThat(productPage.getBonusText())
                    .as("Incorrect bonus quantity text")
                    .contains("бонус");

            productPage.back();
        }
        softAssertions.assertAll();
    }

    @Test
    public void verifyResettingFilters() {

        var searchResultsPage = homePage
                .getHeader()
                .search("Xbox");

        int resultsAmountAfterSearch = searchResultsPage.getResultsAmount();

        var filter = searchResultsPage.getFilter();

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
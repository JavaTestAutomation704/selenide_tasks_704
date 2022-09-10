package com.softserveinc.ita.rozetka;

import com.softserveinc.ita.rozetka.data.Category;
import com.softserveinc.ita.rozetka.data.ProductFilter;
import com.softserveinc.ita.rozetka.data.subcategory.LaptopsAndComputersSubcategory;
import com.softserveinc.ita.rozetka.utils.TestRunner;
import org.assertj.core.api.SoftAssertions;
import org.testng.annotations.Test;

import java.util.List;

import static com.softserveinc.ita.rozetka.data.Category.*;
import static com.softserveinc.ita.rozetka.data.Country.ITALY;
import static com.softserveinc.ita.rozetka.data.Country.SPAIN;
import static com.softserveinc.ita.rozetka.data.ProductFilter.*;
import static com.softserveinc.ita.rozetka.data.ProductSort.PRICE_ASCENDING;
import static com.softserveinc.ita.rozetka.data.ProductSort.PRICE_DESCENDING;
import static com.softserveinc.ita.rozetka.data.subcategory.LaptopsAndComputersSubcategory.NOTEBOOKS;
import static com.softserveinc.ita.rozetka.data.subcategory.LaptopsAndComputersSubcategory.TABLETS;
import static com.softserveinc.ita.rozetka.data.subcategory.PlumbingAndRepairSubcategory.BATHROOM_FURNITURE;
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

        assertThat(searchResultsPage.getProductsQuantity())
                .as("Product quantity should be sufficient")
                .isGreaterThanOrEqualTo(60);

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
        var filter = homePage
                .openCategoryPage(SMARTPHONES_TV_AND_ELECTRONICS)
                .openSubcategoryPage(MOBILE_PHONES)
                .getFilter();
        filter.filter(AVAILABLE);
        var searchResultsPage = filter.filter(WITH_BONUS);
        int productsQuantity = 5;

        assertThat(searchResultsPage.getProductsQuantity())
                .as("Products amount should be sufficient")
                .isGreaterThanOrEqualTo(productsQuantity);

        var softAssertions = new SoftAssertions();
        for (int i = 1; i <= productsQuantity; i++) {
            var productPage = searchResultsPage
                    .getProduct(i)
                    .open();

            //TODO: This test may be failed as unavailable products might be among the results
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

        long resultsAmountAfterSearch = searchResultsPage.getResultsAmount();

        var filter = searchResultsPage.getFilter();

        long resultsAmountAfterFilters = filter
                .filter(List.of(WHITE_COLOR, ROZETKA_SELLER, AVAILABLE))
                .getResultsAmount();

        searchResultsPage.resetFilters();
        filter.filter(MICROSOFT_BRAND);

        long resultsAmountAfterResetting = searchResultsPage.getResultsAmount();

        var softAssert = new SoftAssertions();

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

    @Test
    public void verifyFilterByPrice() {
        var subcategoryPage = homePage
                .openCategoryPage(LAPTOPS_AND_COMPUTERS)
                .openSubcategoryPage(TABLETS);
        subcategoryPage.sortBy(PRICE_ASCENDING);

        var cheapestProductPrice = subcategoryPage
                .getProduct(1)
                .getPrice();

        var filter = subcategoryPage.getFilter();

        var softAssertions = new SoftAssertions();

        softAssertions.assertThat(cheapestProductPrice)
                .as("Product price should be correct")
                .isGreaterThanOrEqualTo(filter.getMinPrice());

        subcategoryPage.sortBy(PRICE_DESCENDING);

        var mostExpensiveProductPrice = subcategoryPage
                .getProduct(1)
                .getPrice();

        softAssertions.assertThat(mostExpensiveProductPrice)
                .as("Product price should be correct")
                .isLessThanOrEqualTo(filter.getMaxPrice());

        var minPrice = 1500;
        var maxPrice = 2500;

        filter.setMinPrice(minPrice);
        filter.setMaxPrice(maxPrice);
        int productsQuantity = 5;

        assertThat(subcategoryPage.getProductsQuantity())
                .as("Products amount should be sufficient")
                .isGreaterThanOrEqualTo(productsQuantity);

        for (int i = 1; i <= productsQuantity; i++) {
            var productPrice = subcategoryPage
                    .getProduct(i)
                    .getPrice();

            softAssertions.assertThat(productPrice)
                    .as("Product price should be correct")
                    .isGreaterThanOrEqualTo(minPrice)
                    .isLessThanOrEqualTo(maxPrice);
        }
        softAssertions.assertAll();
    }

    @Test
    public void verifyProductPreUsedFilter() {
        var subcategoryPage = homePage
                .openCategoryPage(Category.LAPTOPS_AND_COMPUTERS)
                .openSubcategoryPage(LaptopsAndComputersSubcategory.NOTEBOOKS);
        var filter = subcategoryPage.getFilter();
        filter.filter(ProductFilter.PRE_USED);
        int productsQuantity = subcategoryPage.getProductsQuantity();
        int productsQuantityToCheck = 30;

        var softly = new SoftAssertions();

        assertThat(productsQuantity)
                .as("Products quantity should be sufficient")
                .isGreaterThanOrEqualTo(productsQuantityToCheck);

        for (int i = 1; i <= productsQuantity; i = i + 10) {
            var isProductUsed = subcategoryPage
                    .getProduct(i)
                    .isUsed();
            // TODO: This test may be failed as new products might be among the results
            softly.assertThat(isProductUsed)
                    .as("Product should be used")
                    .isTrue();
        }

        subcategoryPage.resetFilters();
        filter.filter(ProductFilter.NEW);

        softly.assertThat(productsQuantity)
                .as("Products quantity should be sufficient")
                .isGreaterThanOrEqualTo(productsQuantityToCheck);

        for (int i = 1; i <= productsQuantity; i = i + 10) {
            var isProductUsed = subcategoryPage
                    .getProduct(i)
                    .isUsed();
            // TODO: This test may be failed as pre-used products might be among the results
            softly.assertThat(isProductUsed)
                    .as("Product should be new")
                    .isFalse();
        }
        softly.assertAll();
    }

    @Test
    public void verifyFilterByProducingCountry() {
        var filter = homePage
                .openCategoryPage(PLUMBING_AND_REPAIR)
                .openSubcategoryPage(BATHROOM_FURNITURE)
                .getFilter();
        var subcategoryPage = filter.filter(PRODUCED_IN_SPAIN);

        int productsQuantity = 5;

        assertThat(subcategoryPage.getProductsQuantity())
                .as("Products amount should be sufficient")
                .isGreaterThanOrEqualTo(productsQuantity);

        var softAssertions = new SoftAssertions();
        for (int i = 1; i <= productsQuantity; i++) {
            var productCharacteristicsPage = subcategoryPage
                    .getProduct(i)
                    .open()
                    .openCharacteristicsPage();

            softAssertions.assertThat(productCharacteristicsPage.getCountryName())
                    .as("Country should be correct")
                    .isEqualTo(SPAIN.getCountryNameUa());

            // In order to return to the search results page, you should use back methods twice
            productCharacteristicsPage.back();
            productCharacteristicsPage.back();
        }

        filter.filter(List.of(PRODUCED_IN_SPAIN, PRODUCED_IN_ITALY));

        assertThat(subcategoryPage.getProductsQuantity())
                .as("Products amount should be sufficient")
                .isGreaterThanOrEqualTo(productsQuantity);

        for (int i = 1; i <= productsQuantity; i++) {
            var productCharacteristicsPage = subcategoryPage
                    .getProduct(i)
                    .open()
                    .openCharacteristicsPage();

            softAssertions.assertThat(productCharacteristicsPage.getCountryName())
                    .as("Country should be correct")
                    .isEqualTo(ITALY.getCountryNameUa());

            // In order to return to the search results page, you should use back methods twice
            productCharacteristicsPage.back();
            productCharacteristicsPage.back();
        }
        softAssertions.assertAll();
    }

    @Test
    public void verifyThatProductIsReadyToBeDelivered() {
        var subcategoryPage = homePage
                .openCategoryPage(Category.LAPTOPS_AND_COMPUTERS)
                .openSubcategoryPage(LaptopsAndComputersSubcategory.NOTEBOOKS);

        var filter = subcategoryPage.getFilter();
        filter.filter(READY_TO_BE_DELIVERED);

        assertThat(filter.isSelected(READY_TO_BE_DELIVERED))
                .as("Filter should be selected")
                .isTrue();

        int productsQuantity = subcategoryPage.getProductsQuantity();
        int productsQuantityToCheck = 10;

        var softly = new SoftAssertions();

        softly.assertThat(productsQuantity)
                .as("Products quantity should be sufficient")
                .isGreaterThanOrEqualTo(productsQuantityToCheck);

        for (int i = 1; i <= productsQuantityToCheck; i++) {
            var productReadyToBeDelivered = subcategoryPage
                    .getProduct(i)
                    .isAvailable();
            //TODO: This test may be failed as unavailable products might be among the results
            softly.assertThat(productReadyToBeDelivered)
                    .as("Product should be ready to be delivered")
                    .isTrue();
        }
        softly.assertAll();
    }

    @Test
    public void verifyThatBrandFilterSearchWorksCorrectly() {
        var filter = homePage
                .getHeader()
                .openCatalogModal()
                .openSubcategory(LAPTOPS_AND_COMPUTERS, NOTEBOOKS)
                .getFilter();

        var searchQueries = List.of("Asus", "Mi", "err", "HP");

        var softAssertions = new SoftAssertions();

        for (var query : searchQueries) {
            filter.searchForBrand(query);
            softAssertions
                    .assertThat(filter.getBrandSearchResults())
                    .allSatisfy(brand -> assertThat(brand).containsIgnoringCase(query));
        }

        filter.clearBrandSearchField();

        var searchLetters = List.of("A", "N", "H", "J");
        var alphabetSidebar = filter.startAlphabeticalSearch();

        for (var letter : searchLetters) {
            alphabetSidebar.searchByLetter(letter);
            softAssertions
                    .assertThat(filter.getBrandSearchResults())
                    .allSatisfy(brand -> assertThat(brand).startsWithIgnoringCase(letter));
        }

        softAssertions.assertAll();
    }
}
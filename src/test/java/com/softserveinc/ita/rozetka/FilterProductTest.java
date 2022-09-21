package com.softserveinc.ita.rozetka;

import com.softserveinc.ita.rozetka.data.Category;
import com.softserveinc.ita.rozetka.data.ProductFilter;
import com.softserveinc.ita.rozetka.data.subcategory.LaptopsAndComputersSubcategory;
import com.softserveinc.ita.rozetka.utils.TestRunner;
import org.assertj.core.api.SoftAssertions;
import org.testng.annotations.Test;

import java.util.List;

import static com.softserveinc.ita.rozetka.data.Category.*;
import static com.softserveinc.ita.rozetka.data.Color.BLACK;
import static com.softserveinc.ita.rozetka.data.Color.WHITE;
import static com.softserveinc.ita.rozetka.data.Country.ITALY;
import static com.softserveinc.ita.rozetka.data.Country.SPAIN;
import static com.softserveinc.ita.rozetka.data.Language.UA;
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
        var searchResultsPage = header.search("телефони");

        assertThat(searchResultsPage.getProductsQuantity())
                .as("Product quantity should be sufficient")
                .isGreaterThanOrEqualTo(60);

        searchResultsPage = searchResultsPage
                .getFilter()
                .filter(ProductFilter.PROMOTION);

        assertThat(searchResultsPage.getProductsQuantity())
                .as("Product quantity should be sufficient")
                .isGreaterThanOrEqualTo(60);

        var softly = new SoftAssertions();
        for (int productNumber : new int[]{2, 40, 60}) {
            var isProductOnSale = searchResultsPage
                    .getProduct(productNumber)
                    .isOnSale();

            // TODO: This test may be failed as product wasn't have sale label or old price
            softly.assertThat(isProductOnSale)
                    .as(productNumber + " product should be on sale")
                    .isTrue();
        }

        softly.assertAll();
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

        var softly = new SoftAssertions();
        for (int i = 1; i <= productsQuantity; i++) {
            var productPage = searchResultsPage
                    .getProduct(i)
                    .open();

            //TODO: This test may be failed as unavailable products might be among the results
            softly.assertThat(productPage.isBonusIconVisible())
                    .as("Bonus icon should be displayed")
                    .isTrue();
            softly.assertThat(productPage.getBonusText())
                    .as("Incorrect bonus quantity text")
                    .contains("бонус");

            productPage.back();
        }
        softly.assertAll();
    }

    @Test
    public void verifyResettingFilters() {
        var header = homePage.getHeader();
        header.changeLanguage(UA);
        var isUaLanguageSelected = header.isLanguageSelected(UA);

        assertThat(isUaLanguageSelected)
                .as("Localization should be switched to UA")
                .isTrue();

        var searchResultsPage = header.search("Xbox");

        long resultsAmountAfterSearch = searchResultsPage.getResultsAmount();

        var filter = searchResultsPage.getFilter();

        long resultsAmountAfterFilters = filter
                .filter(List.of(WHITE_COLOR, ROZETKA_SELLER, AVAILABLE))
                .getResultsAmount();

        searchResultsPage.resetFilters();
        filter.filter(MICROSOFT_BRAND);

        long resultsAmountAfterResetting = searchResultsPage.getResultsAmount();

        var softly = new SoftAssertions();

        softly
                .assertThat(resultsAmountAfterResetting)
                .as("Results amount after resetting should be grater than after filters")
                .isGreaterThan(resultsAmountAfterFilters);
        //TODO: This test may be failed as results amount after resetting may be different than after search
        softly
                .assertThat(resultsAmountAfterResetting)
                .as("Results amount after resetting should be the same as after search")
                .isEqualTo(resultsAmountAfterSearch);

        softly.assertAll();
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

        var softly = new SoftAssertions();

        softly.assertThat(cheapestProductPrice)
                .as("Product price should be correct")
                .isGreaterThanOrEqualTo(filter.getMinPrice());

        subcategoryPage.sortBy(PRICE_DESCENDING);

        var mostExpensiveProductPrice = subcategoryPage
                .getProduct(1)
                .getPrice();

        softly.assertThat(mostExpensiveProductPrice)
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

            softly.assertThat(productPrice)
                    .as("Product price should be correct")
                    .isGreaterThanOrEqualTo(minPrice)
                    .isLessThanOrEqualTo(maxPrice);
        }
        softly.assertAll();
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

        var softly = new SoftAssertions();
        for (int i = 1; i <= productsQuantity; i++) {
            var productCharacteristicsPage = subcategoryPage
                    .getProduct(i)
                    .open()
                    .openCharacteristicsPage();

            softly.assertThat(productCharacteristicsPage.getCountryName())
                    .as("Country should be correct")
                    .isEqualTo(SPAIN.getCountryNameUa());

            // In order to return to the search results page, you should use back methods twice
            productCharacteristicsPage.back();
            productCharacteristicsPage.back();
        }

        filter.filter(PRODUCED_IN_SPAIN);
        filter.filter(PRODUCED_IN_ITALY);

        assertThat(subcategoryPage.getProductsQuantity())
                .as("Products amount should be sufficient")
                .isGreaterThanOrEqualTo(productsQuantity);

        for (int i = 1; i <= productsQuantity; i++) {
            var productCharacteristicsPage = subcategoryPage
                    .getProduct(i)
                    .open()
                    .openCharacteristicsPage();

            softly.assertThat(productCharacteristicsPage.getCountryName())
                    .as("Country should be correct")
                    .isEqualTo(ITALY.getCountryNameUa());

            // In order to return to the search results page, you should use back methods twice
            productCharacteristicsPage.back();
            productCharacteristicsPage.back();
        }
        softly.assertAll();
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

        var softly = new SoftAssertions();

        searchQueries.forEach(query -> {
            filter.searchForBrand(query);
            softly
                    .assertThat(filter.getBrandSearchResults())
                    .allSatisfy(brand -> assertThat(brand)
                            .as("Brand name should contain search query")
                            .containsIgnoringCase(query));
        });

        filter.clearBrandSearchField();

        var alphabetSidebar = filter.startAlphabeticalSearch();
        assertThat(alphabetSidebar.isOpened())
                .as("Alphabet sidebar should be opened")
                .isTrue();

        var searchLetters = List.of("A", "N", "H", "J");
        searchLetters.forEach(letter -> {
            alphabetSidebar.searchByLetter(letter);
            softly
                    .assertThat(filter.getBrandSearchResults())
                    .as("Brand name should start with selected letter or contain that letter")
                    .satisfiesAnyOf(
                            brands -> assertThat(brands)
                                    .allSatisfy(brand -> assertThat(brand)
                                            .as("Brand name should start with selected letter")
                                            .startsWithIgnoringCase(letter)),
                            brands -> assertThat(brands)
                                    .allSatisfy(brand -> assertThat(brand)
                                            .as("Brand name should contains selected letter")
                                            .contains(letter)));
        });

        softly.assertAll();
    }

    @Test
    public void verifyFilterByProductColor() {
        var productPage = homePage
                .openCategoryPage(SMARTPHONES_TV_AND_ELECTRONICS)
                .openSubcategoryPage(MOBILE_PHONES)
                .getFilter()
                .filter(WHITE_COLOR)
                .getProduct(1)
                .open();
        var productCharacteristicsPage = productPage.openCharacteristicsPage();

        var softly = new SoftAssertions();
        softly.assertThat(productCharacteristicsPage.getCharacteristicText())
                .as("Characteristic text should contain correct color")
                .contains(WHITE.getColor());

        productCharacteristicsPage.back();

        softly.assertThat(productPage.getColor())
                .as("Products color should be correct")
                .contains(WHITE.getColor());

        productPage.selectColor(BLACK);

        softly.assertThat(productPage.getColor())
                .as("Products color should be correct")
                .isEqualTo(BLACK.getColor());

        productPage.openCharacteristicsPage();

        softly.assertThat(productCharacteristicsPage.getCharacteristicText())
                .as("Characteristic text should contain correct color")
                .contains(BLACK.getColor());

        softly.assertAll();
    }
}
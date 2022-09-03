package com.softserveinc.ita.rozetka;

import com.softserveinc.ita.rozetka.components.Filter;
import com.softserveinc.ita.rozetka.components.Header;
import com.softserveinc.ita.rozetka.data.Category;
import com.softserveinc.ita.rozetka.data.ProductFilter;
import com.softserveinc.ita.rozetka.data.subcategory.LaptopsAndComputersSubcategory;
import com.softserveinc.ita.rozetka.utils.TestRunner;
import org.assertj.core.api.SoftAssertions;
import org.testng.annotations.Test;

import java.util.List;

import static com.softserveinc.ita.rozetka.data.Category.PLUMBING_AND_REPAIR;
import static com.softserveinc.ita.rozetka.data.Category.SMARTPHONES_TV_AND_ELECTRONICS;
import static com.softserveinc.ita.rozetka.data.Country.ITALY;
import static com.softserveinc.ita.rozetka.data.Country.SPAIN;
import static com.softserveinc.ita.rozetka.data.ProductFilter.*;
import static com.softserveinc.ita.rozetka.data.subcategory.PlumbingAndRepairSubcategory.BATHROOM_FURNITURE;
import static com.softserveinc.ita.rozetka.data.subcategory.SmartphonesTvAndElectronicsSubcategory.MOBILE_PHONES;
import static org.assertj.core.api.Assertions.assertThat;

public class FilterProductTest extends TestRunner {
    @Test
    public void verifySaleFilterFunctionality() {
        Header header = homePage.getHeader();
        SearchResultsPage searchResultsPage = header.search("laptop");

        assertThat(searchResultsPage.getProductsQuantity())
                .as("Product quantity should be sufficient")
                .isGreaterThanOrEqualTo(60);

        searchResultsPage = searchResultsPage
                .getFilter()
                .filter(ProductFilter.PROMOTION);

        SoftAssertions softAssertions = new SoftAssertions();
        for (int productNumber : new int[]{2, 40, 60}) {
            boolean isProductOnSale = searchResultsPage
                    .getProduct(productNumber)
                    .isOnSale();

            softAssertions.assertThat(isProductOnSale)
                    .as(productNumber + " product should be on sale")
                    .isTrue();
        }

        softAssertions.assertAll();
    }


    @Test
    public void verifyProductAvailabilityFilter() {
        var searchResultsPage = homePage
                .openCategoryPage(Category.LAPTOPS_AND_COMPUTERS)
                .openSubcategoryPage(LaptopsAndComputersSubcategory.NOTEBOOKS)
                .getFilter()
                .filter(AVAILABLE);

        int productsQuantity = searchResultsPage.getProductsQuantity();
        int productsQuantityToCheck = 20;

        assertThat(productsQuantity)
                .as("Products quantity should be sufficient")
                .isGreaterThanOrEqualTo(productsQuantityToCheck);

        var softly = new SoftAssertions();

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

    @Test
    public void verifyFilterByCountry() {
        var searchResultsPage = homePage
                .openCategoryPage(PLUMBING_AND_REPAIR)
                .openSubcategoryPage(BATHROOM_FURNITURE)
                .getFilter()
                .filter(SPAIN);

        int productsQuantity = 5;

        assertThat(searchResultsPage.getProductsQuantity())
                .as("Products amount should be sufficient")
                .isGreaterThanOrEqualTo(productsQuantity);

        var softAssertions = new SoftAssertions();
        for (int i = 1; i <= productsQuantity; i++) {
            var productCharacteristicsPage = searchResultsPage
                    .getProduct(i)
                    .open()
                    .openCharacteristicsPage();

            softAssertions.assertThat(productCharacteristicsPage.getCountryName())
                    .as("Country should be correct")
                    .isEqualTo(SPAIN.getCountryNameInUkrainian());

            productCharacteristicsPage.back();
            productCharacteristicsPage.back();
        }

        searchResultsPage
                .getFilter()
                .filter(SPAIN)
                .getFilter()
                .filter(ITALY);

        assertThat(searchResultsPage.getProductsQuantity())
                .as("Products amount should be sufficient")
                .isGreaterThanOrEqualTo(productsQuantity);

        for (int i = 1; i <= productsQuantity; i++) {
            var productCharacteristicsPage = searchResultsPage
                    .getProduct(i)
                    .open()
                    .openCharacteristicsPage();

            softAssertions.assertThat(productCharacteristicsPage.getCountryName())
                    .as("Country should be correct")
                    .isEqualTo(ITALY.getCountryNameInUkrainian());

            productCharacteristicsPage.back();
            productCharacteristicsPage.back();
        }
        softAssertions.assertAll();
    }
}
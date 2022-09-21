package com.softserveinc.ita.rozetka;

import com.softserveinc.ita.rozetka.components.Header;
import com.softserveinc.ita.rozetka.utils.TestRunner;
import org.assertj.core.api.SoftAssertions;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class SearchFunctionalityTest extends TestRunner {

    @Test
    public void verifySearchFunctionalityWorks() {
        var searchPhrase = "starbucks";
        var searchResultsPage = homePage
                .getHeader()
                .search(searchPhrase);

        int productsQuantity = searchResultsPage.getProductsQuantity();
        int productsQuantityToCheck = 20;

        assertThat(productsQuantity)
                .as("Products quantity should be sufficient")
                .isGreaterThanOrEqualTo(productsQuantityToCheck);

        var softly = new SoftAssertions();

        for (int i = 1; i <= productsQuantityToCheck; i = i + 5) {
            softly.assertThat(searchResultsPage.getProduct(i).getTitleLowerCase())
                    .as("Product title should contain searched keyword")
                    .contains(searchPhrase);
        }
        softly.assertAll();
    }

    private Header cleanSearchHistory() {
        var header = homePage.getHeader();
        var searchMenu = header.openSearchMenu();
        if (!searchMenu.isHistoryCleaned()) {
            searchMenu.clearSearchHistory();
        }
        return header;
    }

    @Test
    public void verifyLastSeenProductsFunctionality() {
        var header = cleanSearchHistory();
        var searchResultsPage = header.search("snickers");

        assertThat(searchResultsPage.getProductsQuantity())
                .as("Products quantity should be sufficient")
                .isGreaterThanOrEqualTo(1);

        var firstSearchedProduct = searchResultsPage.getProduct(1);
        var firstSearchedProductName = firstSearchedProduct.getTitle();
        var productPage = firstSearchedProduct.open();

        assertThat(productPage.isOpened())
                .as("First product page should be opened")
                .isTrue();

        header.openHomePageViaLogo();

        var softly = new SoftAssertions();
        softly.assertThat(firstSearchedProduct.isLastSeen(firstSearchedProductName))
                .as("First searched product should be displayed in 'last seen' section at first position")
                .isTrue();

        header.search("lego heroes");

        assertThat(searchResultsPage.getProductsQuantity())
                .as("Products quantity should be sufficient")
                .isGreaterThanOrEqualTo(2);

        var secondSearchedProduct = searchResultsPage.getProduct(2);
        var secondSearchedProductName = secondSearchedProduct.getTitle();
        secondSearchedProduct.open();

        assertThat(productPage.isOpened())
                .as("Second product page should be opened")
                .isTrue();

        header.openHomePageViaLogo();

        softly.assertThat(secondSearchedProduct.isLastSeen(secondSearchedProductName))
                .as("Second searched product should be displayed in 'last seen' section at first position")
                .isTrue();

        softly.assertThat(firstSearchedProduct.isPreviouslySeen(firstSearchedProductName))
                .as("First searched product should be displayed in 'last seen' section at second position")
                .isTrue();
        softly.assertAll();
    }

    @Test
    public void verifyMoreLastSeenProductsAreShownAfterClickingOnShowMoreButton() {
        var header = cleanSearchHistory();
        var keyword = "purina";
        var searchResultsPage = header.search(keyword);
        int productsAmount = 8;

        assertThat(searchResultsPage.getProductsQuantity())
                .as("Products quantity should be sufficient")
                .isGreaterThanOrEqualTo(productsAmount);

        for (int i = 1; i <= productsAmount; i++) {
            var productPage = searchResultsPage.getProduct(i).open();

            assertThat(productPage.isOpened())
                    .as("Product page should be opened")
                    .isTrue();

            homePage.back();

            assertThat(searchResultsPage.doesTitleContain(keyword))
                    .as("Search results page title should contain keyword")
                    .isTrue();
        }

        header.openHomePageViaLogo();

        var lastSeenProductsSection = homePage.getLastSeenProductsSection();
        int minProductsAmount = lastSeenProductsSection.getProductsAmount();

        lastSeenProductsSection.showMoreProducts();

        assertThat(lastSeenProductsSection.areMoreProductsShown(minProductsAmount))
                .as("More last seen products should be shown")
                .isTrue();
    }

    @Test
    public void verifySearchHistoryFunctionality() {
        var header = homePage.getHeader();

        var firstPhrase = "purina";
        var searchResultsPage = header.search(firstPhrase);

        assertThat(searchResultsPage.doesTitleContain(firstPhrase))
                .as("Search should be performed")
                .isTrue();

        header.openHomePageViaLogo();

        var searchMenu = header.openSearchMenu();

        assertThat(searchMenu.isOpened())
                .as("Search menu should be opened")
                .isTrue();

        var softly = new SoftAssertions();
        softly.assertThat(searchMenu.isLastSearched(firstPhrase))
                .as("First search request should be displayed at first position in search menu list")
                .isTrue();

        var secondPhrase = "whiskas";
        header.search(secondPhrase);

        assertThat(searchResultsPage.doesTitleContain(secondPhrase))
                .as("Search should be performed")
                .isTrue();

        header.openHomePageViaLogo();
        header.openSearchMenu();

        assertThat(searchMenu.isOpened())
                .as("Search menu should be opened after second search request is performed")
                .isTrue();

        softly.assertThat(searchMenu.isLastSearched(secondPhrase))
                .as("Second search request should be displayed at first position in search menu list")
                .isTrue();

        softly.assertThat(searchMenu.isPreviouslySearched(firstPhrase))
                .as("First search request should be displayed at second position in search menu list")
                .isTrue();

        searchMenu.cleanSearchRequest(secondPhrase);

        softly.assertThat(searchMenu.isRequestRemoved(secondPhrase))
                .as("First search request should be displayed at first position in search menu list")
                .isTrue();

        softly.assertThat(searchMenu.isLastSearched(firstPhrase))
                .as("First search request should be displayed at first position in search menu list")
                .isTrue();

        searchMenu.clearSearchHistory();

        softly.assertThat(searchMenu.isHistoryCleaned())
                .as("Search menu list should be empty")
                .isTrue();
        softly.assertAll();
    }
}
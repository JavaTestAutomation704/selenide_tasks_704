package com.softserveinc.ita.rozetka;

import com.softserveinc.ita.rozetka.components.Header;
import com.softserveinc.ita.rozetka.components.Product;
import com.softserveinc.ita.rozetka.utils.TestRunner;
import org.assertj.core.api.SoftAssertions;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class SearchFunctionalityTest extends TestRunner {

    @Test
    public void verifySearchFunctionalityWorks() {
        String searchPhrase = "starbucks";
        SearchResultsPage searchResultsPage = homePage
                .getHeader()
                .search(searchPhrase);

        int productsQuantity = searchResultsPage.getProductsQuantity();
        int productsQuantityToCheck = 20;

        assertThat(productsQuantity)
                .as("Products quantity should be sufficient")
                .isGreaterThanOrEqualTo(productsQuantityToCheck);

        SoftAssertions softly = new SoftAssertions();

        for (int i = 1; i <= productsQuantityToCheck; i = i + 5) {
            softly.assertThat(searchResultsPage.getProduct(i).getTitleLowerCase())
                    .as("Product title should contain searched keyword")
                    .contains(searchPhrase);
        }
        softly.assertAll();
    }

    @Test
    public void verifyLastSeenProductsFunctionality() {
        Header header = homePage.getHeader();
        SearchResultsPage searchResultsPage = header.search("snickers");

        assertThat(searchResultsPage.getProductsQuantity())
                .as("Products quantity should be sufficient")
                .isGreaterThanOrEqualTo(1);

        Product firstSearchedProduct = searchResultsPage.getProduct(1);
        String firstSearchedProductName = firstSearchedProduct.getTitle();
        firstSearchedProduct.open();
        header.openHomePageViaLogo();

        SoftAssertions softly = new SoftAssertions();
        softly.assertThat(firstSearchedProduct.isLastSeen(firstSearchedProductName))
                .as("First searched product should be displayed in 'last seen' section at first position")
                .isTrue();

        header.search("lego heroes");

        assertThat(searchResultsPage.getProductsQuantity())
                .as("Products quantity should be sufficient")
                .isGreaterThanOrEqualTo(2);

        Product secondSearchedProduct = searchResultsPage.getProduct(2);
        String secondSearchedProductName = secondSearchedProduct.getTitle();
        secondSearchedProduct.open();
        header.openHomePageViaLogo();

        softly.assertThat(secondSearchedProduct.isLastSeen(secondSearchedProductName))
                .as("Second searched product should be displayed in 'last seen' section at first position")
                .isTrue();

        softly.assertThat(firstSearchedProduct.isPreviouslySeen(firstSearchedProductName))
                .as("First searched product should be displayed in 'last seen' section at second position")
                .isTrue();
        softly.assertAll();
    }
}
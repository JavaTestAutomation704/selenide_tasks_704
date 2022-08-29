package com.softserveinc.ita.rozetka;

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
            softly.assertThat(searchResultsPage.getProduct(i).getTitle())
                    .as("Product title should contain: " + searchPhrase)
                    .contains(searchPhrase);
        }
        softly.assertAll();
    }
}
package com.softserveinc.ita.rozetka;

import com.softserveinc.ita.rozetka.components.Header;
import com.softserveinc.ita.rozetka.data.ProductSort;
import com.softserveinc.ita.rozetka.utils.TestRunner;
import org.assertj.core.api.SoftAssertions;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class SortingProductTest extends TestRunner {
    @Test
    public void verifyProductSaleSorting() {
        Header header = homePage.getHeader();
        SearchResultsPage searchResultsPage = header.search("laptop");

        assertThat(searchResultsPage.getProductsQuantity())
                .as("Product quantity should be sufficient")
                .isGreaterThanOrEqualTo(60);

        searchResultsPage = searchResultsPage.sortBy(ProductSort.PROMOTION);

        SoftAssertions softAssertions = new SoftAssertions();

        for (int productNumber : new int[]{1, 25, 59}) {
            boolean isProductOnSale = searchResultsPage
                    .getProduct(productNumber)
                    .isOnSale();

            softAssertions.assertThat(isProductOnSale)
                    .as("Product with number %s should be on sale", productNumber)
                    .isTrue();
        }
        softAssertions.assertAll();
    }
}

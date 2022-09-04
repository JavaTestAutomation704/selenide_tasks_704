package com.softserveinc.ita.rozetka;

import com.softserveinc.ita.rozetka.utils.TestRunner;
import org.assertj.core.api.SoftAssertions;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class DiscountsTest extends TestRunner {

    @Test
    public void verifyDiscountCalculationCorrectness() {

        var searchResultsPage = homePage
                .getHeader()
                .search("asus");

        int productsQuantity = searchResultsPage.getProductsWithDiscountQuantity();
        int productsQuantityToCheck = 3;

        assertThat(productsQuantity)
                .as("The products with discount quantity should be sufficient on the search results page")
                .isGreaterThanOrEqualTo(productsQuantityToCheck);

        var softAssertions = new SoftAssertions();

        for (int i = 1; i <= productsQuantityToCheck; i++) {
            var product = searchResultsPage.getProductWithDiscount(i);

            int discount = product.getDiscount();
            double currentPrice = product.getCurrentPrice();
            double oldPrice = product.getOldPrice();

            int calculatedDiscount = (int) (oldPrice / currentPrice * 10);

            softAssertions
                    .assertThat(calculatedDiscount)
                    .as("The price should be correctly calculated according to the discount")
                    .isEqualTo(discount);
        }
    }
}
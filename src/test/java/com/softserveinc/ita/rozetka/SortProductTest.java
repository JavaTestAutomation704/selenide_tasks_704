package com.softserveinc.ita.rozetka;

import com.softserveinc.ita.rozetka.data.ProductSort;
import com.softserveinc.ita.rozetka.utils.TestRunner;
import org.assertj.core.api.SoftAssertions;
import org.testng.annotations.Test;

import static com.softserveinc.ita.rozetka.data.Category.LAPTOPS_AND_COMPUTERS;
import static com.softserveinc.ita.rozetka.data.ProductFilter.AVAILABLE;
import static com.softserveinc.ita.rozetka.data.ProductSort.*;
import static com.softserveinc.ita.rozetka.data.subcategory.LaptopsAndComputersSubcategory.ASUS;
import static org.assertj.core.api.Assertions.assertThat;

public class SortProductTest extends TestRunner {

    @Test
    public void verifyProductsSortingInAscendingOrderByPrice() {
        var subcategoryPage = homePage
                .getHeader()
                .openCatalogModal()
                .openSubcategory(LAPTOPS_AND_COMPUTERS, ASUS)
                .getFilter()
                .filter(AVAILABLE)
                .sortBy(PRICE_ASCENDING);

        var softAssert = new SoftAssertions();
        int step = 4;
        int subcategoryProductQuantity = subcategoryPage.getProductsQuantity();

        softAssert.assertThat(subcategoryProductQuantity)
                .as("Products quantity should be sufficient")
                .isGreaterThanOrEqualTo(step + 1);

        for (int i = step + 1; i < subcategoryProductQuantity; i += step) {
            softAssert
                    .assertThat(subcategoryPage.getProduct(i).getPrice())
                    .as(i + "th product price should be higher than th" + (i - step))
                    .isGreaterThanOrEqualTo(subcategoryPage.getProduct(i - step).getPrice());
        }
        softAssert.assertAll();
    }

    @Test
    public void verifyProductsSortingInDescendingOrderByPrice() {
        var subcategoryPage = homePage
                .getHeader()
                .openCatalogModal()
                .openSubcategory(LAPTOPS_AND_COMPUTERS, ASUS)
                .getFilter()
                .filter(AVAILABLE);

        int productsQuantity = subcategoryPage.getProductsQuantity();
        assertThat(productsQuantity)
                .as("Products quantity should be sufficient")
                .isGreaterThanOrEqualTo(20);

        subcategoryPage.sortBy(PRICE_DESCENDING);

        assertThat(subcategoryPage
                .getProduct(1)
                .getPrice())
                .as("First product price should be higher than last product price")
                .isGreaterThan(subcategoryPage
                        .getProduct("last")
                        .getPrice());

        int step = 6;
        var softly = new SoftAssertions();
        for (int i = 1; i <= productsQuantity - step; i += step) {
            softly.assertThat(subcategoryPage
                            .getProduct(i)
                            .getTitleLowerCase())
                    .as(i + "th product title should contain keyword")
                    .contains("asus");
            softly.assertThat(subcategoryPage
                            .getProduct(i)
                            .getPrice())
                    .as("%dth product price should be higher than %dth product price", i, i + step)
                    .isGreaterThan(subcategoryPage
                            .getProduct(i + step)
                            .getPrice());
        }
        softly.assertAll();
    }


    @Test
    public void verifySaleSortFunctionality() {
        var header = homePage.getHeader();
        var searchResultsPage = header.search("laptop");

        assertThat(searchResultsPage.getProductsQuantity())
                .as("Product quantity should be sufficient")
                .isGreaterThanOrEqualTo(60);

        searchResultsPage = searchResultsPage.sortBy(ProductSort.PROMOTION);

        var softAssertions = new SoftAssertions();

        for (int productNumber : new int[]{1, 25, 59}) {
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

}
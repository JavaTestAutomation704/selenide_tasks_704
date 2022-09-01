package com.softserveinc.ita.rozetka;

import com.softserveinc.ita.rozetka.data.Category;
import com.softserveinc.ita.rozetka.data.ProductSort;
import com.softserveinc.ita.rozetka.data.subcategory.LaptopsAndComputersSubcategory;
import com.softserveinc.ita.rozetka.utils.TestRunner;
import org.assertj.core.api.SoftAssertions;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class SortProductTest extends TestRunner {

    @Test
    public void verifyProductsSortingInAscendingOrderByPrice() {
        SubcategoryPage subcategoryPage = homePage
                .getHeader()
                .openCatalogModal()
                .openSubcategory(Category.LAPTOPS_AND_COMPUTERS, LaptopsAndComputersSubcategory.ASUS);

        subcategoryPage.sortBy(ProductSort.PRICE_ASCENDING);
        SoftAssertions softAssert = new SoftAssertions();
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
        SubcategoryPage subcategoryPage = homePage
                .getHeader()
                .openCatalogModal()
                .openSubcategory(Category.LAPTOPS_AND_COMPUTERS, LaptopsAndComputersSubcategory.ASUS);

        int productsQuantity = subcategoryPage.getProductsQuantity();
        assertThat(productsQuantity)
                .as("Products quantity should be sufficient")
                .isGreaterThanOrEqualTo(20);

        subcategoryPage.sortBy(ProductSort.PRICE_DESCENDING);

        assertThat(subcategoryPage
                .getProduct(1)
                .getPrice())
                .as("First product price should be higher than last product price")
                .isGreaterThan(subcategoryPage
                        .getProduct("last")
                        .getPrice());

        int step = 6;
        SoftAssertions softly = new SoftAssertions();
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
}
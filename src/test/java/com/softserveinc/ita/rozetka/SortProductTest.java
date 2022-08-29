package com.softserveinc.ita.rozetka;

import com.softserveinc.ita.rozetka.components.Product;
import com.softserveinc.ita.rozetka.data.Category;
import com.softserveinc.ita.rozetka.data.ProductSort;
import com.softserveinc.ita.rozetka.data.subcategory.modal.LaptopsAndComputers;
import org.assertj.core.api.SoftAssertions;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class SortProductTest extends TestRunner {
    @Test
    public void verifyProductsSortingInDescendingOrderByPrice() {
        SubcategoryPage subcategoryPage = homePage
                .getHeader()
                .openCatalogModal()
                .openSubcategory(Category.LAPTOPS_AND_COMPUTERS, LaptopsAndComputers.ASUS);

        int step = 6;
        int productsQuantity = subcategoryPage.getProductsQuantity();
        assertThat(productsQuantity)
                .as("Products quantity should be sufficient.")
                .isGreaterThan(step);

        subcategoryPage.sortBy(ProductSort.PRICE_DESCENDING);

        SoftAssertions softly = new SoftAssertions();
        Product currentProduct;
        long currentProductPrice = 0;
        for (int i = 1; i <= productsQuantity - step; i += step) {
            currentProduct = subcategoryPage.getProduct(i);
            currentProductPrice = currentProduct.getPrice();
            softly.assertThat( currentProduct.getTitle())
                    .as("%dth product title should contain keyword.", i)
                    .contains("asus");
            softly.assertThat(currentProductPrice)
                    .as("%dth product price should be higher than %dth product price.", i, i + step)
                    .isGreaterThan(subcategoryPage.getProduct(i + step).getPrice());
        }

        softly.assertThat(currentProductPrice)
                .as("Current product price should be higher than last product price.")
                .isGreaterThan(subcategoryPage.getProduct("last").getPrice());
        softly.assertAll();
    }
}
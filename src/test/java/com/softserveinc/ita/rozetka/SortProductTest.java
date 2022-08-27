package com.softserveinc.ita.rozetka;

import com.softserveinc.ita.rozetka.data.Category;
import com.softserveinc.ita.rozetka.data.ProductSort;
import com.softserveinc.ita.rozetka.data.subcategory.modal.LaptopsAndComputers;
import org.assertj.core.api.SoftAssertions;
import org.testng.annotations.Test;

public class SortProductTest extends TestRunner {
    @Test
    public void verifyUserCanSortProductsInDescendingOrderByPrice() {
        SubcategoryPage products = homePage
                .getHeader()
                .openCatalogModal()
                .openSubcategory(Category.LAPTOPS_AND_COMPUTERS, LaptopsAndComputers.ASUS);

        String firstProductTitle = products.getProduct(1).getTitle();
        String lastProductTitle = products.getProduct("last").getTitle();
        String categoryKeyword = "asus";

        SoftAssertions softly = new SoftAssertions();
        softly.assertThat(firstProductTitle)
                .as("First product title")
                .contains(categoryKeyword);
        softly.assertThat(lastProductTitle)
                .as("Last product title")
                .contains(categoryKeyword);

        products.sortBy(ProductSort.PRICE_DESCENDING);

        long currentProductPrice = 0;
        int step = 8;
        for (int i = 1; i <= products.getProductsSize() - step; i += step) {
            currentProductPrice = products.getProduct(i).getPrice();
            softly.assertThat(currentProductPrice)
                    .as("Current product price in comparison to next")
                    .isGreaterThan(products.getProduct(i + step).getPrice());
        }
        long lastProductPrice = products.getProduct("last").getPrice();
        softly.assertThat(currentProductPrice)
                .as("Current product price in comparison to next")
                .isGreaterThan(lastProductPrice);
        softly.assertAll();
    }
}
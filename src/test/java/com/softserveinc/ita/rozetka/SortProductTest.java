package com.softserveinc.ita.rozetka;

import com.softserveinc.ita.rozetka.components.Product;
import com.softserveinc.ita.rozetka.data.Category;
import com.softserveinc.ita.rozetka.data.ProductSort;
import com.softserveinc.ita.rozetka.data.subcategory.modal.LaptopsAndComputers;
import org.assertj.core.api.SoftAssertions;
import org.testng.annotations.Test;

public class SortProductTest extends TestRunner {
    @Test
    public void verifyUserCanSortProductsInDescendingOrderByPrice() {
        SubcategoryPage subcategoryPage = homePage
                .getHeader()
                .openCatalogModal()
                .openSubcategory(Category.LAPTOPS_AND_COMPUTERS, LaptopsAndComputers.ASUS);

        String firstProductTitle = subcategoryPage.getProduct(1).getTitle();
        Product lastProduct = subcategoryPage.getProduct("last");
        String categoryKeyword = "asus";

        SoftAssertions softly = new SoftAssertions();
        softly.assertThat(firstProductTitle)
                .as("First product title should contain keyword.")
                .contains(categoryKeyword);
        softly.assertThat( lastProduct.getTitle())
                .as("Last product title should contain keyword.")
                .contains(categoryKeyword);

        subcategoryPage.sortBy(ProductSort.PRICE_DESCENDING);

        long currentProductPrice = 0;
        int step = 8;
        int productsQuantity = subcategoryPage.getProductsQuantity();
        for (int i = 1; i <= productsQuantity - step; i += step) {
            currentProductPrice = subcategoryPage.getProduct(i).getPrice();
            softly.assertThat(currentProductPrice)
                    .as("Current product price should be higher than %sth next product price.", step)
                    .isGreaterThan(subcategoryPage.getProduct(i + step).getPrice());
        }

        softly.assertThat(currentProductPrice)
                .as("Current product price should be higher than last product price.")
                .isGreaterThan(lastProduct.getPrice());
        softly.assertAll();
    }
}
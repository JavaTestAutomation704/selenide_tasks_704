package com.softserveinc.ita.rozetka;

import com.softserveinc.ita.rozetka.components.Filter;
import com.softserveinc.ita.rozetka.data.Category;
import com.softserveinc.ita.rozetka.data.ProductFilter;
import com.softserveinc.ita.rozetka.data.subcategory.modal.LaptopsAndComputers;
import org.assertj.core.api.SoftAssertions;
import org.testng.annotations.Test;

public class FilterProductsOnConditionTest extends TestRunner {

    @Test
    public void verifyProductConditionFilter() {
        SubcategoryPage subcategoryPage = homePage
                .openCategoryPage(Category.LAPTOPS_AND_COMPUTERS)
                .openSubcategoryPage(LaptopsAndComputers.NOTEBOOKS);
        Filter filter = subcategoryPage.getFilter();
        filter.filter(ProductFilter.PRE_USED);
        int productsQuantity = subcategoryPage.getProductsQuantity();
        int productsQuantityToCheck = 10;

        SoftAssertions softly = new SoftAssertions();

        softly.assertThat(productsQuantity)
                .as("Products quantity should be sufficient")
                .isGreaterThanOrEqualTo(productsQuantityToCheck);
        for (int i = 1; i <= productsQuantity; i = i + 10) {
            softly.assertThat(subcategoryPage.getProduct(i).isUsed())
                    .as("Product should be used")
                    .isTrue();
        }

        subcategoryPage.resetFilters();
        filter.filter(ProductFilter.NEW);

        softly.assertThat(productsQuantity)
                .as("Products quantity should be sufficient")
                .isGreaterThanOrEqualTo(productsQuantityToCheck);
        for (int i = 1; i <= subcategoryPage.getProductsQuantity(); i = i + 10) {
            softly.assertThat(subcategoryPage.getProduct(i).isUsed())
                    .as("Product should be new")
                    .isFalse();
        }
        softly.assertAll();
    }
}

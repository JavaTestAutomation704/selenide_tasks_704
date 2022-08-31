package com.softserveinc.ita.rozetka;

import com.softserveinc.ita.rozetka.components.Filter;
import com.softserveinc.ita.rozetka.data.Category;
import com.softserveinc.ita.rozetka.data.ProductFilter;
import com.softserveinc.ita.rozetka.data.subcategory.LaptopsAndComputersSubcategory;
import com.softserveinc.ita.rozetka.utils.TestRunner;
import org.assertj.core.api.SoftAssertions;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class FilterProductOnConditionTest extends TestRunner {

    @Test
    public void verifyProductPreUsedFilter() {
        SubcategoryPage subcategoryPage = homePage
                .openCategoryPage(Category.LAPTOPS_AND_COMPUTERS)
                .openSubcategoryPage(LaptopsAndComputersSubcategory.NOTEBOOKS);
        Filter filter = subcategoryPage.getFilter();
        filter.filter(ProductFilter.PRE_USED);
        int productsQuantity = subcategoryPage.getProductsQuantity();
        int productsQuantityToCheck = 30;

        SoftAssertions softly = new SoftAssertions();

        assertThat(productsQuantity)
                .as("Products quantity should be sufficient")
                .isGreaterThanOrEqualTo(productsQuantityToCheck);

        for (int i = 1; i <= productsQuantity; i = i + 10) {
            boolean isProductUsed = subcategoryPage
                    .getProduct(i)
                    .isUsed();
            softly.assertThat(isProductUsed)
                    .as("Product should be used")
                    .isTrue();
        }

        subcategoryPage.resetFilters();
        filter.filter(ProductFilter.NEW);

        softly.assertThat(productsQuantity)
                .as("Products quantity should be sufficient")
                .isGreaterThanOrEqualTo(productsQuantityToCheck);

        for (int i = 1; i <= productsQuantity; i = i + 10) {
            boolean isProductUsed = subcategoryPage
                    .getProduct(i)
                    .isUsed();
            softly.assertThat(isProductUsed)
                    .as("Product should be new")
                    .isFalse();
        }
        softly.assertAll();
    }
}

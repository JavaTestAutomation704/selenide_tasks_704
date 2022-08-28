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
        SearchResultsPage searchResultsPage = homePage
                .openCategoryPage(Category.LAPTOPS_AND_COMPUTERS)
                .openSubcategoryPage(LaptopsAndComputers.NOTEBOOKS);
        Filter filterSidebar = searchResultsPage.getFilter();
        filterSidebar.filter(ProductFilter.PRE_USED);

        SoftAssertions softly = new SoftAssertions();
        for (int i = 1; i <= searchResultsPage.getProductsSize(); i = i + 10) {
            softly.assertThat(searchResultsPage.getProduct(i).isUsed())
                    .as("Product is used")
                    .isTrue();
        }

        searchResultsPage.resetFilters();
        filterSidebar.filter(ProductFilter.NEW);

        for (int i = 1; i <= searchResultsPage.getProductsSize(); i = i + 10) {
            softly.assertThat(searchResultsPage.getProduct(i).isUsed())
                    .as("Product is new")
                    .isFalse();
        }
        softly.assertAll();
    }
}

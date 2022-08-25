package com.softserveinc.ita.rozetka;

import com.softserveinc.ita.rozetka.data.Category;
import com.softserveinc.ita.rozetka.data.ProductFilter;
import com.softserveinc.ita.rozetka.data.Subcategory;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class FilterProductsOnConditionTest extends TestRunner {

    @Test
    public void verifyProductConditionFilter() {
        SearchResultsPage searchResultsPage = homePage
                .openCategoryPage(Category.NOTEBOOKS_COMPUTERS)
                .openSubcategoryPage(Subcategory.NOTEBOOKS)
                .getFilter()
                .filter(ProductFilter.PRE_USED);

        SoftAssert softAssert = new SoftAssert();
        for (int i = 1; i <= 60; i = i + 10) {
            softAssert.assertTrue(searchResultsPage.getProduct(i).isUsed());
        }

        searchResultsPage
                .resetFilters()
                .getFilter()
                .filter(ProductFilter.NEW);

        for (int i = 1; i <= 60; i = i + 10) {
            softAssert.assertFalse(searchResultsPage.getProduct(i).isUsed());
        }
    }
}

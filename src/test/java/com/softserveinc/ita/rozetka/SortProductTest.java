package com.softserveinc.ita.rozetka;

import com.softserveinc.ita.rozetka.data.Category;
import com.softserveinc.ita.rozetka.data.ProductSort;
import com.softserveinc.ita.rozetka.data.subcategory.page.HouseholdAppliances;
import org.assertj.core.api.SoftAssertions;
import org.testng.annotations.Test;

public class SortProductTest extends TestRunner {
    @Test
    public void verifyUserCanSortProductsInAscendingOrderByPrice() {
        SearchResultsPage searchResultsPage = homePage
                .openCategoryPage(Category.HOUSEHOLD_APPLIANCES)
                .openSubcategoryPage(HouseholdAppliances.REFRIGERATORS);

        searchResultsPage.sortBy(ProductSort.PRICE_ASCENDING);
        SoftAssertions softAssert = new SoftAssertions();

        if (searchResultsPage.getProductsSize() >= 1) {
            for (int i = 2; i < searchResultsPage.getProductsSize(); i++) {
                softAssert
                        .assertThat(searchResultsPage.getProduct(i).getPrice() >= searchResultsPage.getProduct(i-1).getPrice())
                        .withFailMessage(String.format("%d product price should be higher than %d",
                                searchResultsPage.getProduct(i).getPrice(), searchResultsPage.getProduct(i).getPrice()));
            }
        }
    }
}
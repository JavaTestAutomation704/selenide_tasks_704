package com.softserveinc.ita.rozetka;

import com.softserveinc.ita.rozetka.data.ProductFilter;
import com.softserveinc.ita.rozetka.data.ProductSort;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class FilterProductTest extends TestRunner {
    @Test
    public void saleSortTest() {
        String searchItem = "laptop";
        SearchResultsPage searchResultsPage = homePage
                .getHeader()
                .search(searchItem)
                .sortBy(ProductSort.PROMOTION);
        SoftAssert softAssert = new SoftAssert();

        softAssert.assertTrue(searchResultsPage.isOnSale(1));
        softAssert.assertTrue(searchResultsPage.isOnSale(15));
        softAssert.assertTrue(searchResultsPage.isOnSale(60));

        searchResultsPage = searchResultsPage
                .getHeader()
                .search(searchItem)
                .getFilter()
                .filter(ProductFilter.PROMOTION);

        softAssert.assertTrue(searchResultsPage.isOnSale(2));
        softAssert.assertTrue(searchResultsPage.isOnSale(15));
        softAssert.assertTrue(searchResultsPage.isOnSale(58));

        softAssert.assertAll();
    }
}

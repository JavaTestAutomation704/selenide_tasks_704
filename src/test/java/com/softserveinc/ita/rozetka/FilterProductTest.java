package com.softserveinc.ita.rozetka;

import com.softserveinc.ita.rozetka.components.Header;
import com.softserveinc.ita.rozetka.data.ProductFilter;
import com.softserveinc.ita.rozetka.data.ProductSort;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class FilterProductTest extends TestRunner {
    @Test
    public void verifySaleSort() {
        Header header = homePage.getHeader();
        SearchResultsPage searchResultsPage = header
                .search("laptop")
                .sortBy(ProductSort.PROMOTION);

        SoftAssert softAssert = new SoftAssert();
        //will be added assert on product count after merge

        softAssert.assertTrue(searchResultsPage.getProduct(1).isOnSale());
        softAssert.assertTrue(searchResultsPage.getProduct(25).isOnSale());
        softAssert.assertTrue(searchResultsPage.getProduct(59).isOnSale());

        searchResultsPage = searchResultsPage
                .sortBy(ProductSort.RATING)
                .getFilter()
                .filter(ProductFilter.PROMOTION);

        softAssert.assertTrue(searchResultsPage.getProduct(2).isOnSale());
        softAssert.assertTrue(searchResultsPage.getProduct(40).isOnSale());
        softAssert.assertTrue(searchResultsPage.getProduct(60).isOnSale());
    }



    @Test
    public void verifyResettingFilters() {
        SearchResultsPage searchResultsPage = homePage
                .getHeader()
                .search("Xbox");

        int resultsAmountAfterSearch = searchResultsPage.getResultsAmount();

        Filter filter = searchResultsPage.getFilter();

        int resultsAmountAfterFilters = filter
                .filter(List.of(WHITE_COLOR, ROZETKA_SELLER, AVAILABLE))
                .getResultsAmount();

        searchResultsPage.resetFilters();
        filter.filter(MICROSOFT_BRAND);

        int resultsAmountAfterResetting = searchResultsPage.getResultsAmount();

        SoftAssert softAssert = new SoftAssert();

        softAssert.assertTrue(resultsAmountAfterResetting > resultsAmountAfterFilters);
        softAssert.assertTrue(resultsAmountAfterResetting == resultsAmountAfterSearch);

        softAssert.assertAll();
    }
}

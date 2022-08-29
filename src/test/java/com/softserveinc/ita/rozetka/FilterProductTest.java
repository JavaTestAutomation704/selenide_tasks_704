package com.softserveinc.ita.rozetka;

import com.softserveinc.ita.rozetka.components.Filter;
import com.softserveinc.ita.rozetka.components.Header;
import com.softserveinc.ita.rozetka.data.ProductFilter;
import com.softserveinc.ita.rozetka.data.ProductSort;
import org.assertj.core.api.SoftAssertions;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.List;

import static com.softserveinc.ita.rozetka.data.ProductFilter.*;

public class FilterProductTest extends TestRunner {
    @Test
    public void verifyUserCanSortProductsByPromotion() {
        Header header = homePage.getHeader();
        SearchResultsPage searchResultsPage = header
                .search("laptop")
                .sortBy(ProductSort.PROMOTION);

        SoftAssertions softAssertions = new SoftAssertions();
        softAssertions.assertThat(searchResultsPage.getProductsSize())
                .isGreaterThanOrEqualTo(60)
                .as("Products should be 60 or more");

        for (int productNumber : new int[]{1, 25, 59}) {
            softAssertions.assertThat(searchResultsPage.getProduct(productNumber).isOnSale())
                    .isTrue()
                    .as(String.format("Product with number %s should be on sale", productNumber));
        }

        searchResultsPage = searchResultsPage
                .sortBy(ProductSort.RATING)
                .getFilter()
                .filter(ProductFilter.PROMOTION);

        for (int productNumber : new int[]{2, 40, 60}) {
            softAssertions.assertThat(searchResultsPage.getProduct(productNumber).isOnSale())
                    .isTrue()
                    .as(String.format("Product with number %s should be on sale", productNumber));
        }

        softAssertions.assertAll();
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

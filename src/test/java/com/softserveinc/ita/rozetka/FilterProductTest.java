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
    public void verifySaleSort() {
        Header header = homePage.getHeader();
        SearchResultsPage searchResultsPage = header
                .search("laptop")
                .sortBy(ProductSort.PROMOTION);

        //will be added assert on product count after merge
        SoftAssertions softAssertions = new SoftAssertions();
        String errorMessage = "Product with number \"%s\" isn't on sale";
        int productNumber = 1;
        softAssertions.assertThat(searchResultsPage.getProduct(productNumber).isOnSale())
                .withFailMessage(String.format(errorMessage, productNumber))
                .isTrue();

        productNumber = 25;
        softAssertions.assertThat(searchResultsPage.getProduct(productNumber).isOnSale())
                .withFailMessage(String.format(errorMessage, productNumber))
                .isTrue();

        productNumber = 59;
        softAssertions.assertThat(searchResultsPage.getProduct(productNumber).isOnSale())
                .withFailMessage(String.format(errorMessage, productNumber))
                .isTrue();

        searchResultsPage = searchResultsPage
                .sortBy(ProductSort.RATING)
                .getFilter()
                .filter(ProductFilter.PROMOTION);

        productNumber = 2;
        softAssertions.assertThat(searchResultsPage.getProduct(productNumber).isOnSale())
                .withFailMessage(String.format(errorMessage, productNumber))
                .isTrue();

        productNumber = 40;
        softAssertions.assertThat(searchResultsPage.getProduct(productNumber).isOnSale())
                .withFailMessage(String.format(errorMessage, productNumber))
                .isTrue();

        productNumber = 60;
        softAssertions.assertThat(searchResultsPage.getProduct(productNumber).isOnSale())
                .withFailMessage(String.format(errorMessage, productNumber))
                .isTrue();

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

package com.softserveinc.ita.rozetka;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.List;

import static com.softserveinc.ita.rozetka.data.ProductFilter.*;

public class FilterProductTest extends TestRunner {

    @Test
    public void verifyResettingFilters() {
        SearchResultsPage searchResultsPage = new SearchResultsPage();

        homePage
                .getHeader()
                .search("Xbox");

        int resultsAmountAfterSearch = searchResultsPage.getResultsAmount();

        int resultsAmountAfterFilters = searchResultsPage
                .getFilter()
                .filter(List.of(WHITE_COLOR, ROZETKA_SELLER, AVAILABLE))
                .getResultsAmount();

        searchResultsPage
                .resetFilters()
                .getFilter()
                .filter(MICROSOFT_BRAND);


        int resultsAmountAfterResetting = searchResultsPage.getResultsAmount();

        SoftAssert softAssert = new SoftAssert();

        softAssert.assertTrue(resultsAmountAfterResetting > resultsAmountAfterFilters);
        softAssert.assertTrue(resultsAmountAfterResetting == resultsAmountAfterSearch);

        softAssert.assertAll();
    }
}

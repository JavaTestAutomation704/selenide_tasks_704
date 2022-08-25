package com.softserveinc.ita.rozetka;

import com.softserveinc.ita.rozetka.components.ResultsFilter;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import static com.softserveinc.ita.rozetka.data.Category.NOTEBOOKS_COMPUTERS;
import static com.softserveinc.ita.rozetka.data.ProductFilter.AVAILABLE;
import static com.softserveinc.ita.rozetka.data.ProductFilter.WITH_BONUS;
import static com.softserveinc.ita.rozetka.data.Subcategory.NOTEBOOKS;

public class FilterProductTest extends TestRunner {

    @Test
    public void VerifyFilterByLoyaltyProgram() {
        int[] numbers = {1, 5, 9};
        ResultsFilter resultsFilter = homePage
                .openCategoryPage(NOTEBOOKS_COMPUTERS)
                .openSubcategoryPage(NOTEBOOKS)
                .getFilter();
        resultsFilter.filter(AVAILABLE);
        SearchResultsPage searchResultsPage = resultsFilter.filter(WITH_BONUS);
        SoftAssert softAssert = new SoftAssert();
        for (int number : numbers) {
            ProductPage productPage = searchResultsPage
                    .getProduct(number)
                    .open();
            softAssert.assertTrue(productPage.isBonusIconVisible());
            softAssert.assertTrue(productPage.getBonusText().contains("бонус"));
            productPage.back();
        }
        softAssert.assertAll();
    }
}
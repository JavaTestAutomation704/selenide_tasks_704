package com.softserveinc.ita.rozetka;

import com.softserveinc.ita.rozetka.components.Filter;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.List;

import static com.softserveinc.ita.rozetka.data.ProductFilter.*;
import static com.softserveinc.ita.rozetka.data.Category.LAPTOPS_AND_COMPUTERS;
import static com.softserveinc.ita.rozetka.data.ProductFilter.AVAILABLE;
import static com.softserveinc.ita.rozetka.data.ProductFilter.WITH_BONUS;
import static com.softserveinc.ita.rozetka.data.subcategory.page.LaptopsAndComputers.NOTEBOOKS;
import static org.assertj.core.api.Assertions.*;

public class FilterProductTest extends TestRunner {

    @Test
    public void verifyFilterByLoyaltyProgram() {
        Filter resultsFilter = homePage
                .openCategoryPage(LAPTOPS_AND_COMPUTERS)
                .openSubcategoryPage(NOTEBOOKS)
                .getFilter();
        resultsFilter.filter(AVAILABLE);
        SearchResultsPage searchResultsPage = resultsFilter.filter(WITH_BONUS);
        int productsAmount = 9;

        Assert.assertTrue(productsAmount <= searchResultsPage.getProductsSize());

        for (int i = 1; i < productsAmount; i += 2) {
            ProductPage productPage = searchResultsPage
                    .getProduct(i)
                    .open();

            assertThat(productPage.isBonusIconVisible()).isTrue();
            assertThat(productPage.getBonusText()).contains("бонус");

            productPage.back();
        }
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

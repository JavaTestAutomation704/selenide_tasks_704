package com.softserveinc.ita.rozetka;

import com.softserveinc.ita.rozetka.components.Filter;
import com.softserveinc.ita.rozetka.data.Availability;
import com.softserveinc.ita.rozetka.data.Category;
import com.softserveinc.ita.rozetka.data.subcategory.page.LaptopsAndComputers;
import org.assertj.core.api.SoftAssertions;
import org.testng.annotations.Test;

import java.util.List;

import static com.softserveinc.ita.rozetka.data.ProductFilter.*;
import static org.assertj.core.api.Assertions.assertThat;

public class FilterProductTest extends TestRunner {

    @Test
    public void verifyProductAvailabilityFilter() {
        SearchResultsPage searchResultsPage = homePage
                .openCategoryPage(Category.LAPTOPS_AND_COMPUTERS)
                .openSubcategoryPage(LaptopsAndComputers.NOTEBOOKS)
                .getFilter()
                .filter(AVAILABLE);

        int productsAmount = searchResultsPage.getResultsAmount();

        assertThat(productsAmount)
                .as("Products amount should be at least 20")
                .isGreaterThanOrEqualTo(20);

        SoftAssertions softly = new SoftAssertions();

        for (int i = 1; i <= 20; i++) {
            Availability actualAvailability = searchResultsPage
                    .getProduct(i)
                    .getAvailability();

            softly.assertThat(actualAvailability)
                    .as("Product should be available")
                    .isIn(List.of(Availability.AVAILABLE,
                            Availability.READY_TO_BE_DELIVERED,
                            Availability.RUNNING_OUT_OF_STOCK));
        }
        softly.assertAll();
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

        SoftAssertions softAssert = new SoftAssertions();

        softAssert
                .assertThat(resultsAmountAfterResetting)
                .as("Results amount after resetting should be grater than after filters")
                .isGreaterThan(resultsAmountAfterFilters);
        softAssert
                .assertThat(resultsAmountAfterResetting)
                .as("Results amount after resetting should be the same as after search")
                .isEqualTo(resultsAmountAfterSearch);

        softAssert.assertAll();
    }
}
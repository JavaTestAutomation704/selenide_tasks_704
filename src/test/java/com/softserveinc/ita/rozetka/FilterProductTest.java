package com.softserveinc.ita.rozetka;

import com.softserveinc.ita.rozetka.components.Filter;
import com.softserveinc.ita.rozetka.data.Category;
import com.softserveinc.ita.rozetka.data.subcategory.page.LaptopsAndComputers;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.ArrayList;
import java.util.List;

import static com.softserveinc.ita.rozetka.data.ProductFilter.*;

public class FilterProductTest extends TestRunner {

    @Test
    public void verifyProductAvailabilityFilter() {
        SearchResultsPage searchResultsPage = homePage
                .openCategoryPage(Category.LAPTOPS_AND_COMPUTERS)
                .openSubcategoryPage(LaptopsAndComputers.NOTEBOOKS)
                .getFilter()
                .filter(AVAILABLE);

        List<String> productAvailability = new ArrayList<>();
        for (int i = 1; i <= 20; i++) {
            productAvailability.add(searchResultsPage.getProduct(i).getAvailability());
        }

        SoftAssert softAssert = new SoftAssert();
        productAvailability
                .forEach(availability ->
                        softAssert.assertTrue(availability.contains("Готовий до відправлення")
                                        || availability.contains("Є в наявності"),
                                "The product status is: " + availability));
        @Test
        public void verifyResettingFilters () {
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
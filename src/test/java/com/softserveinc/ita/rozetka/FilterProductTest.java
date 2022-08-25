package com.softserveinc.ita.rozetka;

import com.softserveinc.ita.rozetka.data.Category;
import com.softserveinc.ita.rozetka.data.Subcategory;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.ArrayList;
import java.util.List;

import static com.softserveinc.ita.rozetka.data.ProductFilter.AVAILABLE;

public class FilterProductTest extends TestRunner {

    @Test
    public void verifyProductAvailabilityFilter() {
        SearchResultsPage searchResultsPage = homePage
                .openCategoryPage(Category.NOTEBOOKS_COMPUTERS)
                .openSubcategoryPage(Subcategory.NOTEBOOKS)
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
        softAssert.assertAll();
    }
}

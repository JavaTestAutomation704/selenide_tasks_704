package com.softserveinc.ita.rozetka;

import com.softserveinc.ita.rozetka.data.Category;
import com.softserveinc.ita.rozetka.data.Subcategory;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.ArrayList;
import java.util.List;

import static com.softserveinc.ita.rozetka.data.Category.NOTEBOOKS_COMPUTERS;
import static com.softserveinc.ita.rozetka.data.ProductFilter.AVAILABLE;
import static com.softserveinc.ita.rozetka.data.ProductFilter.WITH_BONUS;
import static com.softserveinc.ita.rozetka.data.Subcategory.NOTEBOOKS;
import static org.testng.Assert.assertTrue;


public class FilterProductTest extends TestRunner {

    @Test
    public void VerifyFilterByLoyaltyProgram() {
        boolean isBonusIconVisible = homePage
                .openCategoryPage(NOTEBOOKS_COMPUTERS)
                .openSubcategoryPage(NOTEBOOKS)
                .getFilter()
                .filter(WITH_BONUS)
                .get(5)
                .open()
                .isBonusIconVisible();
        assertTrue(isBonusIconVisible);
    }

    @Test
    public void verifyProductAvailabilityFilter() {
        homePage
                .openCategoryPage(Category.NOTEBOOKS_COMPUTERS)
                .openSubcategoryPage(Subcategory.NOTEBOOKS)
                .getFilter()
                .filter(AVAILABLE);

        List<String> productAvailability = new ArrayList<>();
        for (int i = 1; i <= 20; i++) {
            productAvailability.add(new SearchResultsPage().get(i).getAvailability());
        }

        SoftAssert softAssert = new SoftAssert();
        productAvailability
                .forEach(availability -> softAssert.assertTrue(availability.contains("Готовий до відправлення")
                                || availability.contains("Є у наявності"),
                        "Product is not available"));
        softAssert.assertAll();
    }
}

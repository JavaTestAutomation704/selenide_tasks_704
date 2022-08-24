package com.softserveinc.ita.rozetka;

import org.testng.annotations.Test;

import static com.softserveinc.ita.rozetka.data.Category.NOTEBOOKS_COMPUTERS;
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
}
package com.softserveinc.ita.rozetka;

import com.softserveinc.ita.rozetka.data.Category;
import com.softserveinc.ita.rozetka.data.subcategory.page.HouseholdAppliances;
import org.assertj.core.api.SoftAssertions;
import org.testng.annotations.Test;

public class ProductTitleTest extends TestRunner {
    @Test
    public void verifyThatProductTitleContainsTheSameTitleAsCard() {

        SearchResultsPage searchResultsPage = homePage
                .openCategoryPage(Category.HOUSEHOLD_APPLIANCES)
                .openSubcategoryPage(HouseholdAppliances.REFRIGERATORS);

        SoftAssertions softAssert = new SoftAssertions();
        String expectedText = "Холодильник";

        for (int i = 1; i <= searchResultsPage.getProductsSize(); i++) {
             String actualTitle = searchResultsPage
                    .getProduct(i)
                    .getTitle();

            softAssert.assertThat(actualTitle).contains(expectedText);
        }
        softAssert.assertAll();
    }
}
package com.softserveinc.ita.rozetka;

import com.softserveinc.ita.rozetka.data.Category;
import com.softserveinc.ita.rozetka.data.subcategory.modal.LaptopsAndComputers;
import org.assertj.core.api.SoftAssertions;
import org.testng.annotations.Test;

public class ProductTitleTest extends TestRunner {
    @Test
    public void verifyThatProductTitleContainsTheSameTitleAsCard() {

        SubcategoryPage subcategoryPage = homePage
                .getHeader()
                .openCatalogModal()
                .openSubcategory(Category.LAPTOPS_AND_COMPUTERS, LaptopsAndComputers.ASUS);

        SoftAssertions softAssert = new SoftAssertions();
        String expectedText = "ноутбук";

        if (subcategoryPage.getProductsQuantity() >= 1) {
            for (int i = 1; i <= subcategoryPage.getProductsQuantity(); i++) {
                String actualTitle = subcategoryPage
                        .getProduct(i)
                        .getTitle();

                softAssert.assertThat(actualTitle).contains(expectedText);
            }
            softAssert.assertAll();
        }
    }
}
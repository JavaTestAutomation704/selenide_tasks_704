package com.softserveinc.ita.rozetka;

import com.softserveinc.ita.rozetka.data.Category;
import com.softserveinc.ita.rozetka.data.subcategory.LaptopsAndComputersSubcategory;
import com.softserveinc.ita.rozetka.utils.TestRunner;
import org.assertj.core.api.SoftAssertions;
import org.testng.annotations.Test;

public class ProductTitleTest extends TestRunner {
    @Test
    public void verifyThatProductTitleContainsTheSameTitleAsCard() {

        SubcategoryPage subcategoryPage = homePage
                .getHeader()
                .openCatalogModal()
                .openSubcategory(Category.LAPTOPS_AND_COMPUTERS, LaptopsAndComputersSubcategory.ASUS);

        SoftAssertions softAssert = new SoftAssertions();
        String expectedText = "ноутбук";

        int productsQuantity = 10;

        softAssert.assertThat(subcategoryPage.getProductsQuantity())
                .as("Products amount should be sufficient")
                .isGreaterThanOrEqualTo(productsQuantity);

        for (int i = 1; i <= productsQuantity; i += 5) {
            ProductPage productPage = subcategoryPage
                    .getProduct(i)
                    .open();

            String actualProductTitle = productPage.getTitle();

            softAssert.assertThat(actualProductTitle)
                    .contains(expectedText)
                    .as(actualProductTitle + " should contain %s text", expectedText);

            productPage.back();
        }
        softAssert.assertAll();
    }
}
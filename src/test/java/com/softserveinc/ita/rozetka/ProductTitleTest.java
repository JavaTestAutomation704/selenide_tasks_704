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

        int productsQuantity = 10;

        softAssert.assertThat(subcategoryPage.getProductsQuantity())
                .as("Products amount should be sufficient")
                .isGreaterThanOrEqualTo(productsQuantity);

        for (int i = 1; i <= productsQuantity; i += 5) {
            ProductPage productPage = subcategoryPage
                    .getProduct(i).open();

            String actualText = productPage.getTitle();
            softAssert.assertThat(actualText.contains(expectedText))
                    .as(actualText + "Product title should contain %s text", expectedText)
                    .isTrue();

            productPage.back();
        }
        softAssert.assertAll();
    }
}
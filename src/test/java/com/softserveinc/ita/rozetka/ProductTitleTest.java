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

        int productsQuantity = 20;

        softAssert.assertThat(productsQuantity)
                .as("Products amount should be sufficient")
                .isLessThanOrEqualTo(subcategoryPage.getProductsQuantity());

        for (int i = 1; i <= productsQuantity; i += 2) {
            ProductPage productPage = subcategoryPage
                    .getProduct(i).open();

            softAssert.assertThat(productPage.getTitle().matches(expectedText))
                    .as("Product title should contain %s text", expectedText)
                    .isTrue();

            productPage.back();
        }
    }
}
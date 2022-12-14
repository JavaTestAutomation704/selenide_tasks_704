package com.softserveinc.ita.rozetka;

import com.softserveinc.ita.rozetka.data.Category;
import com.softserveinc.ita.rozetka.data.subcategory.LaptopsAndComputersSubcategory;
import com.softserveinc.ita.rozetka.utils.BaseTestRunner;
import org.assertj.core.api.SoftAssertions;
import org.testng.annotations.Test;

public class ProductTitleTest extends BaseTestRunner {

    @Test
    public void verifyThatProductTitleContainsTheSameTitleAsCard() {

        var subcategoryPage = homePage
                .getHeader()
                .openCatalogModal()
                .openSubcategory(Category.LAPTOPS_AND_COMPUTERS, LaptopsAndComputersSubcategory.ASUS);

        var softly = new SoftAssertions();
        var expectedText = "ноутбук";

        int productsQuantity = 10;

        softly.assertThat(subcategoryPage.getProductsQuantity())
                .as("Products amount should be sufficient")
                .isGreaterThanOrEqualTo(productsQuantity);

        for (int i = 1; i <= productsQuantity; i += 5) {
            var productPage = subcategoryPage
                    .getProduct(i)
                    .open();

            var actualProductTitle = productPage.getTitle();

            softly.assertThat(actualProductTitle)
                    .contains(expectedText)
                    .as(actualProductTitle + " should contain %s text", expectedText);

            productPage.back();
        }
        softly.assertAll();
    }
}
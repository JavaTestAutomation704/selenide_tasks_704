package com.softserveinc.ita.rozetka;

import com.softserveinc.ita.rozetka.data.Category;
import com.softserveinc.ita.rozetka.data.ProductSort;
import com.softserveinc.ita.rozetka.data.subcategory.modal.LaptopsAndComputers;
import org.assertj.core.api.SoftAssertions;
import org.testng.annotations.Test;

public class SortProductTest extends TestRunner {
    @Test
    public void verifyUserCanSortProductsInAscendingOrderByPrice() {
        SubcategoryPage subcategoryPage = homePage
                .getHeader()
                .openCatalogModal()
                .openSubcategory(Category.LAPTOPS_AND_COMPUTERS, LaptopsAndComputers.ASUS);

        subcategoryPage.sortBy(ProductSort.PRICE_ASCENDING);
        SoftAssertions softAssert = new SoftAssertions();

        softAssert.assertThat(subcategoryPage.getProductsQuantity())
                .as("Products quantity should be sufficient")
                .isGreaterThanOrEqualTo(2);

        for (int i = 2; i < subcategoryPage.getProductsQuantity(); i++) {
            softAssert
                    .assertThat(subcategoryPage.getProduct(i).getPrice() >= subcategoryPage.getProduct(i - 1).getPrice())
                    .as(String.format("%d product price should be higher than %d",
                            subcategoryPage.getProduct(i).getPrice(), subcategoryPage.getProduct(i).getPrice()));
        }
    }
}
package com.softserveinc.ita.rozetka;

import com.softserveinc.ita.rozetka.data.Category;
import com.softserveinc.ita.rozetka.data.ProductSort;
import com.softserveinc.ita.rozetka.data.subcategory.modal.LaptopsAndComputers;
import org.assertj.core.api.SoftAssertions;
import org.testng.annotations.Test;

public class SortProductTest extends TestRunner {
    @Test
    public void verifyProductsSortingInAscendingOrderByPrice() {
        SubcategoryPage subcategoryPage = homePage
                .getHeader()
                .openCatalogModal()
                .openSubcategory(Category.LAPTOPS_AND_COMPUTERS, LaptopsAndComputers.ASUS);

        subcategoryPage.sortBy(ProductSort.PRICE_ASCENDING);
        SoftAssertions softAssert = new SoftAssertions();
        int step = 4;

        softAssert.assertThat(subcategoryPage.getProductsQuantity())
                .as("Products quantity should be sufficient")
                .isGreaterThanOrEqualTo(step + 1);

        for (int i = step + 1; i < subcategoryPage.getProductsQuantity(); i += step) {
            softAssert
                    .assertThat(subcategoryPage.getProduct(i).getPrice())
                    .as(i + "th product price should be higher than th" + (i - step))
                    .isGreaterThanOrEqualTo(subcategoryPage.getProduct(i - step).getPrice());
        }
        softAssert.assertAll();
    }
}
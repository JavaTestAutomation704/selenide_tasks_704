package com.softserveinc.ita.rozetka;

import com.softserveinc.ita.rozetka.data.Category;
import com.softserveinc.ita.rozetka.data.ProductSort;
import com.softserveinc.ita.rozetka.data.subcategory.page.HouseholdAppliances;
import org.assertj.core.api.SoftAssertions;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

public class SortProductTest extends TestRunner {
    @Test
    public void verifyUserCanSortProductsInAscendingOrderByPriceTest() {
        SearchResultsPage products = homePage
                .openCategoryPage(Category.HOUSEHOLD_APPLIANCES)
                .openSubcategoryPage(HouseholdAppliances.REFRIGERATORS);

        products.sortBy(ProductSort.PRICE_ASCENDING);

        List<Long> productPrice = new ArrayList<>(products.getProductsSize());
        SoftAssertions softAssert = new SoftAssertions();

        for (int i = 1; i < products.getProductsSize(); i++) {
            productPrice.add(products.getProduct(i).getPrice());
            if ((i % 3 == 0) && (i > 2)) {
                softAssert
                        .assertThat(productPrice.get(i - 1) >= productPrice.get(i - 2))
                        .withFailMessage(String.format("%d product price should be higher than %d",
                                productPrice.get(i - 1), productPrice.get(i - 2)));
            }
        }
    }
}
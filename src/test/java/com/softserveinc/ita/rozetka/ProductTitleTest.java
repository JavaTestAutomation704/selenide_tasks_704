package com.softserveinc.ita.rozetka;

import com.softserveinc.ita.rozetka.data.Category;
import com.softserveinc.ita.rozetka.data.subcategory.page.HouseholdAppliances;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

import static org.testng.Assert.assertTrue;

public class ProductTitleTest extends TestRunner {

    @Test
    public void verifyThatProductTitleContainsTheSameTitleAsCard() {
        SubcategoryPage products = homePage
                .getHeader()
                .openCatalogModal()
                .openSubcategory(Category.HOUSEHOLD_APPLIANCES, HouseholdAppliances.REFRIGERATORS);

        String productName = "Холодильник";

        List<String> productNameList = new ArrayList<>();
        for (int i = 1; i < 10; i++) {
            productNameList.add(products.getProduct(i).getTitle());
        }

        productNameList.forEach(productTitle -> assertTrue(productTitle.contains(productName),
                String.format("Product title doesn't contain '%s'", productName)));
    }
}

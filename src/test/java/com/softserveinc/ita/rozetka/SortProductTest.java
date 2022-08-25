package com.softserveinc.ita.rozetka;

import com.softserveinc.ita.rozetka.data.Category;
import com.softserveinc.ita.rozetka.data.ProductSort;
import com.softserveinc.ita.rozetka.data.subcategory.page.HouseholdAppliances;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class SortProductTest extends TestRunner {
    @Test
    public void verifyUserCanSortProductsInAscendingOrderByPriceTest() {
        SubcategoryPage products = homePage
                .getHeader()
                .openCatalogModal()
                .openSubcategory(Category.HOUSEHOLD_APPLIANCES, HouseholdAppliances.REFRIGERATORS);

        products.sortBy(ProductSort.PRICE_ASCENDING);

        long firstProductPrice = products.getProduct(1).getPrice();
        long secondProductPrice = products.getProduct(2).getPrice();
        long thirdProductPrice = products.getProduct(3).getPrice();

        assertTrue(firstProductPrice < secondProductPrice,
                String.format("Second product price: %d, should be higher than first: %d\n",
                        firstProductPrice, secondProductPrice));
        assertTrue(secondProductPrice < thirdProductPrice,
                String.format("Third product price: %d, should be higher than second: %d\n",
                        secondProductPrice, thirdProductPrice));
    }
}
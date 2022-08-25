package com.softserveinc.ita.rozetka;

import com.softserveinc.ita.rozetka.data.Category;
import com.softserveinc.ita.rozetka.data.ProductSort;
import com.softserveinc.ita.rozetka.data.Subcategory;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class SortProductTest extends TestRunner {
    @Test
    public void verifyUserCanSortProductsInDescendingOrderByPriceTest() {
        Subcategory subcategory = Subcategory.ASUS;
        String subcategoryName = "asus";

        SubcategoryPage products = homePage
                .getHeader()
                .openCatalogModal()
                .openSubcategory(Category.NOTEBOOKS_COMPUTERS, subcategory);

        String firstProductTitle = products.getProduct(1).getTitle();
        String lastProductTitle = products.getProduct("last").getTitle();
        assertTrue(firstProductTitle.contains(subcategoryName),
                String.format("\nFirst product title '%s' does not contain '%s' subcategory name\n", firstProductTitle, subcategoryName));
        assertTrue(lastProductTitle.contains(subcategoryName),
                String.format("\nFirst product title '%s' does not contain '%s' subcategory name\n", lastProductTitle, subcategoryName));

        products.sortBy(ProductSort.PRICE_DESCENDING);

        long firstProductPrice = products.getProduct(1).getPrice();
        long secondProductPrice = products.getProduct(2).getPrice();
        long lastProductPrice = products.getProduct("last").getPrice();
        assertTrue(firstProductPrice > secondProductPrice,
                String.format("\nFirst product price: %d, should be higher than second: %d\n", firstProductPrice, secondProductPrice));
        assertTrue(firstProductPrice > lastProductPrice,
                String.format("\nFirst product price: %d, should be higher than second: %d\n", firstProductPrice, lastProductPrice));
    }
}
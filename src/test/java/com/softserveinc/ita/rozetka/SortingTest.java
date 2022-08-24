package com.softserveinc.ita.rozetka;

import com.softserveinc.ita.rozetka.data.Category;
import com.softserveinc.ita.rozetka.data.ProductSort;
import com.softserveinc.ita.rozetka.data.Subcategory;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.ArrayList;
import java.util.List;

import static com.softserveinc.ita.rozetka.data.ProductFilter.AVAILABLE;

public class SortingTest extends TestRunner{
    @Test
    public void ascendingSortByPrice() {
        homePage
                .openCategoryPage(Category.NOTEBOOKS_COMPUTERS)
                .openSubcategoryPage(Subcategory.NOTEBOOKS)
                 .sortBy(ProductSort.PRICE_ASCENDING)
        ;

        SearchResultsPage searchResultsPage = new SearchResultsPage();
        long firstProductPrice = searchResultsPage.get(1).getPrice();
        long secondProductPrice = searchResultsPage.get(2).getPrice();

       boolean actualResult = false;

       if(firstProductPrice < secondProductPrice){
           actualResult = true;
       }

        Assert.assertEquals(actualResult, true,
                "Ascending sort works incorrectly");
    }
}

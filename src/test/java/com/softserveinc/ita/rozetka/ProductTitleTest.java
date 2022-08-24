package com.softserveinc.ita.rozetka;

import com.softserveinc.ita.rozetka.data.Category;
import com.softserveinc.ita.rozetka.data.ProductSort;
import com.softserveinc.ita.rozetka.data.Subcategory;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.ArrayList;
import java.util.List;

public class ProductTitleTest extends TestRunner {

    @Test
    public void verifyProductTitleContainsTheSameTitleAsCard() {
        //this method will be changed
        SearchResultsPage searchResultsPage = new SearchResultsPage();
        homePage
                .openCategoryPage(Category.NOTEBOOKS_COMPUTERS)
                .openSubcategoryPage(Subcategory.NOTEBOOKS);

        List<String> productTitles = new ArrayList<>();
        productTitles.add(searchResultsPage.get(1).getTitle());
        productTitles.add(searchResultsPage.get(5).getTitle());
        productTitles.add(searchResultsPage.get(10).getTitle());
        productTitles.add(searchResultsPage.get(20).getTitle());

        SoftAssert softAssert = new SoftAssert();
        productTitles.forEach(productTitle -> softAssert.assertTrue(productTitle.contains("Ноутбук"),
                "Product title doesn't contain 'Ноутбук'"));
        softAssert.assertAll();
    }
}

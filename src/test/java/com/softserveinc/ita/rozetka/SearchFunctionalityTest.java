package com.softserveinc.ita.rozetka;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.ArrayList;
import java.util.List;

public class SearchFunctionalityTest extends TestRunner {

    @Test
    public void verifySearchFunctionalityWorks() {
        SearchResultsPage searchResultsPage = homePage
                .getHeader()
                .search("starbucks");

        List<String> productTitles = new ArrayList<>();
        for (int i = 1; i <= 20; i = i + 5) {
            productTitles.add(searchResultsPage.getProduct(i).getTitle());
        }
        SoftAssert softAssert = new SoftAssert();
        productTitles.forEach(productTitle -> softAssert.assertTrue(productTitle.contains("starbucks"),
                "Product title doesn't contain 'starbucks'"));
        softAssert.assertAll();
    }
}
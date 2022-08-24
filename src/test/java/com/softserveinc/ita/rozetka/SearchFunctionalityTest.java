package com.softserveinc.ita.rozetka;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.ArrayList;
import java.util.List;

public class SearchFunctionalityTest extends TestRunner {

    @Test
    public void verifySearchFunctionalityWorks() {
        SearchResultsPage searchResultsPage = new SearchResultsPage();
        homePage
                .getHeader()
                .search("starbucks");

        List<String> productTitles = new ArrayList<>();
        productTitles.add(searchResultsPage.get(1).getTitle());
        productTitles.add(searchResultsPage.get(5).getTitle());
        productTitles.add(searchResultsPage.get(10).getTitle());
        productTitles.add(searchResultsPage.get(20).getTitle());

        SoftAssert softAssert = new SoftAssert();
        productTitles.forEach(productTitle -> softAssert.assertTrue(productTitle.contains("starbucks"),
                "Product title doesn't contain 'starbucks'"));
        softAssert.assertAll();
    }
}
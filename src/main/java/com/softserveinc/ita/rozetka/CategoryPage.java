package com.softserveinc.ita.rozetka;

import com.softserveinc.ita.rozetka.data.Subcategories;

import static com.codeborne.selenide.Selenide.$x;

public class CategoryPage extends BasePage {

    public SearchResultsPage openSubcategoryPage(Subcategories subcategory) {
        $x(String.format("//a[contains(@class, 'tile-cats__heading') and contains(@href, '%s')]",
                subcategory.getSubcategoryXpath())).click();
        return new SearchResultsPage();
    }
}
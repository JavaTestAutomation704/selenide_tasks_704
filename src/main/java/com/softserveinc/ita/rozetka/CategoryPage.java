package com.softserveinc.ita.rozetka;

import com.softserveinc.ita.rozetka.data.Subcategory;

import static com.codeborne.selenide.Selenide.$x;

public class CategoryPage extends BasePage {

    public SubcategoryPage openSubcategoryPage(Subcategory subcategory) {
        $x(String.format("//a[contains(@class, 'tile-cats__heading') and contains(@href, '%s')]",
                subcategory.getSubcategoryXpath())).click();
        return new SubcategoryPage();
    }
}
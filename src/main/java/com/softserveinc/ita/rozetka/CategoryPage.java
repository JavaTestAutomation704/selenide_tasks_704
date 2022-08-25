package com.softserveinc.ita.rozetka;

import com.softserveinc.ita.rozetka.data.Subcategory;

import static com.codeborne.selenide.Selenide.$x;

public class CategoryPage extends BasePage {
    public SubcategoryPage openSubcategoryPage(Subcategory subcategory) {
        $x(String.format("(//div[@class='tile-cats']//a[contains(@href, '%s')])[1]",
                subcategory.getSubcategoryXpath())).click();
        return new SubcategoryPage();
    }
}
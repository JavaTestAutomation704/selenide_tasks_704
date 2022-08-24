package com.softserveinc.ita.rozetka.modals;

import com.softserveinc.ita.rozetka.SubcategoryPage;
import com.softserveinc.ita.rozetka.data.Category;
import com.softserveinc.ita.rozetka.data.Subcategory;

import static com.codeborne.selenide.Selenide.$x;
import static utils.WebElementUtil.*;

public class CatalogModal {
    public SubcategoryPage openSubcategory(Category category, Subcategory subcategory){
        String categoryXpath = String.format("//a[contains(@href, %s)]/ancestor::li[contains(@class, 'menu-categories__item')]", category.getCategoryXpath());
        $x(categoryXpath).hover();
        waitVisibility(String.format("%s//a[@class='menu__link' and contains(@href, '%s')]",
                categoryXpath,
                subcategory.getSubcategoryXpath())
        ).click();
        return new SubcategoryPage();
    }
}
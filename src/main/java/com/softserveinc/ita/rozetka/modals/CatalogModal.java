package com.softserveinc.ita.rozetka.modals;

import com.softserveinc.ita.rozetka.SubcategoryPage;
import com.softserveinc.ita.rozetka.data.Category;
import com.softserveinc.ita.rozetka.data.subcategory.ISubcategory;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$x;

public class CatalogModal {
    @Step("Catalog modal: open {subcategory} page of {category}")
    public SubcategoryPage openSubcategory(Category category, ISubcategory subcategory) {
        String categoryXpath = String.format("//a[contains(@href, %s)]/ancestor::li[contains(@class, 'menu-categories__item')]", category.getCategoryXpath());
        $x(categoryXpath).hover();
        $x(String.format("%s//div[@class='menu__main-cats-inner']//a[contains(@href, '%s')]", categoryXpath, subcategory.getSubcategoryXpath())).click();
        return new SubcategoryPage();
    }
}
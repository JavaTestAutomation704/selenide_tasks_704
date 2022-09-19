package com.softserveinc.ita.rozetka.modals;

import com.softserveinc.ita.rozetka.SubcategoryPage;
import com.softserveinc.ita.rozetka.data.Category;
import com.softserveinc.ita.rozetka.data.subcategory.ISubcategory;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$x;
import static com.codeborne.selenide.Selenide.actions;
import static java.lang.String.format;

public class CatalogModal {
    @Step("Catalog modal: open {subcategory} page of {category}")
    public SubcategoryPage openSubcategory(Category category, ISubcategory subcategory) {
        var categoryLinkXpath = format("//ul[contains(@class, 'menu-categories')]/li/a[contains(@class, 'link') and contains(@href, '%s')]", category.getCategoryXpath());
        actions().moveToElement($x(categoryLinkXpath)).perform();

        $x(format("%s/parent::li//div[@class='menu__main-cats-inner']//a[contains(@href, '%s')]", categoryLinkXpath, subcategory.getSubcategoryXpath())).click();
        return new SubcategoryPage();
    }
}
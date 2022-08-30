package com.softserveinc.ita.rozetka;

import com.softserveinc.ita.rozetka.data.subcategory.ISubcategory;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$x;

public class CategoryPage extends BasePage {
    @Step("Subcategory page: open subcategory page {subcategory}")
    public SubcategoryPage openSubcategoryPage(ISubcategory subcategory) {
        $x(String.format("(//div[@class='tile-cats']//a[contains(@href, '%s')])[1]",
                subcategory.getSubcategoryXpath())).click();
        return new SubcategoryPage();
    }
}
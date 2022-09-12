package com.softserveinc.ita.rozetka;

import com.softserveinc.ita.rozetka.data.subcategory.ISubcategory;
import io.qameta.allure.Step;

import static com.codeborne.selenide.ClickOptions.usingJavaScript;
import static com.codeborne.selenide.Selenide.$x;

public class CategoryPage extends BasePage {
    @Step("Category page: open subcategory page {subcategory}")
    public SubcategoryPage openSubcategoryPage(ISubcategory subcategory) {
        $x(String.format("(//div[@class='tile-cats']//a[contains(@href, '%s')])[1]",
                subcategory.getSubcategoryXpath()))
                .scrollIntoView(false)
                .click(usingJavaScript());
        return new SubcategoryPage();
    }

    public PromoPage getPromoPage() {
        return new PromoPage();
    }
}
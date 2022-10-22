package com.softserveinc.ita.rozetka;

import com.softserveinc.ita.rozetka.data.subcategory.ISubcategory;
import io.qameta.allure.Step;

import static com.codeborne.selenide.ClickOptions.usingJavaScript;
import static com.codeborne.selenide.Selenide.$x;
import static java.lang.String.format;

public class CategoryPage extends BasePage {

    @Step("Category page: open subcategory page {subcategory}")
    public SubcategoryPage openSubcategoryPage(ISubcategory subcategory) {
        $x(format("(//div[@class='tile-cats']//a[contains(@href, '%s')])[1]",
                subcategory.getSubcategoryXpath()))
                .scrollIntoView(false)
                .click(usingJavaScript());
        return new SubcategoryPage();
    }

    @Step("Category page: open promo page")
    public PromoPage openPromoPage() {
        return new PromoPage();
    }
}
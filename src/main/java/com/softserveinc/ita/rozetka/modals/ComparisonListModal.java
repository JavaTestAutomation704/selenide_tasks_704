package com.softserveinc.ita.rozetka.modals;

import com.softserveinc.ita.rozetka.ComparisonPage;
import com.softserveinc.ita.rozetka.data.subcategory.ISubcategory;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$x;

public class ComparisonListModal {
    @Step("Comparison list modal: open comparison page for {subcategory}")
    public ComparisonPage openComparisonPage(ISubcategory subcategory) {
        $x(String.format("//rz-comparison-modal//a[contains(text(), '%s')]", subcategory.getSubcategoryNameUa())).click();
        return new ComparisonPage();
    }
}
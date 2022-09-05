package com.softserveinc.ita.rozetka.modals;

import com.softserveinc.ita.rozetka.ComparisonPage;
import com.softserveinc.ita.rozetka.components.Header;
import com.softserveinc.ita.rozetka.data.subcategory.ISubcategory;
import io.qameta.allure.Step;

import static com.codeborne.selenide.ClickOptions.usingJavaScript;
import static com.codeborne.selenide.Selenide.$x;
import static com.softserveinc.ita.rozetka.utils.WebElementUtil.getCollectionSize;

public class ComparisonListModal {
    @Step("Comparison list modal: open comparison page for {subcategory}")
    public ComparisonPage openComparisonPage(ISubcategory subcategory) {
        $x(String.format("//rz-comparison-modal//a[contains(text(), '%s')]", subcategory.getSubcategoryNameUa())).click();
        return new ComparisonPage();
    }

    @Step("Comparison list modal: clear comparison list")
    public ComparisonListModal clear() {
        var removeCategoryButtonXpath = "//button[contains(@class, 'remove')]";
        int removeCategoryButtonsQuantity = getCollectionSize(removeCategoryButtonXpath);
        for (int i = removeCategoryButtonsQuantity; i > 0 ; i--) {
            $x(String.format("(%s)[%d]", removeCategoryButtonXpath, i)).click();
        }
        return this;
    }

    @Step("Comparison list modal: close comparison list modal")
    public Header close() {
        $x("//button[contains(@class, 'modal__close')]").click(usingJavaScript());
        return new Header();
    }
}
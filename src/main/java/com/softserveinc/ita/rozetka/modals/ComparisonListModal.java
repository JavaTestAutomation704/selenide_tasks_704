package com.softserveinc.ita.rozetka.modals;

import com.softserveinc.ita.rozetka.ComparisonPage;
import com.softserveinc.ita.rozetka.components.Header;
import com.softserveinc.ita.rozetka.data.subcategory.ISubcategory;
import io.qameta.allure.Step;

import static com.codeborne.selenide.ClickOptions.usingJavaScript;
import static com.codeborne.selenide.Selenide.$x;
import static com.softserveinc.ita.rozetka.utils.WebElementUtil.*;
import static java.lang.String.format;

public class ComparisonListModal {

    private final String categoryXpath = "//rz-comparison-modal//li[contains(@class, 'item')]";
    private final String removeCategoryButtonXpath = categoryXpath + "//button[contains(@class, 'remove')]";

    @Step("Comparison list modal: open comparison page for {subcategory}")
    public ComparisonPage openComparisonPage(ISubcategory subcategory) {
        $x(format("//rz-comparison-modal//a[contains(text(), '%s')]", subcategory.getSubcategoryNameUa())).click();
        return new ComparisonPage();
    }

    @Step("Comparison list modal: remove {category} category from comparison list")
    public ComparisonListModal remove(int category) {
        var comparisonIconCounter = $x("//rz-comparison//rz-icon-counter");
        var initialCounterValue = getNumber(comparisonIconCounter);

        var categoryQuantityXpath = categoryXpath + "//span[contains(@class, 'quantity')]";
        var categoryQuantity = getNumber(format("(%s)[%d]", categoryQuantityXpath, category));

        var categoryRemoveButton = $x(format("(%s)[%d]", removeCategoryButtonXpath, category));
        categoryRemoveButton.click();

        waitInvisibility(categoryRemoveButton);
        if (initialCounterValue - categoryQuantity > 0) {
            waitText(comparisonIconCounter, String.valueOf(initialCounterValue - categoryQuantity));
        } else {
            waitInvisibility(comparisonIconCounter);
        }
        return this;
    }

    @Step("Comparison list modal: clear comparison list")
    public ComparisonListModal clear() {
        int removeCategoryButtonsQuantity = getCollectionSize(removeCategoryButtonXpath);
        for (int i = removeCategoryButtonsQuantity; i > 0; i--) {
            this.remove(i);
        }
        return this;
    }

    @Step("Comparison list modal: close comparison list modal")
    public Header close() {
        $x("//button[contains(@class, 'modal__close')]").click(usingJavaScript());
        return new Header();
    }
}
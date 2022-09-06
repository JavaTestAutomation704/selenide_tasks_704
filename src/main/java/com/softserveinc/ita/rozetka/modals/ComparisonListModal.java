package com.softserveinc.ita.rozetka.modals;

import com.softserveinc.ita.rozetka.ComparisonPage;
import com.softserveinc.ita.rozetka.components.Header;
import com.softserveinc.ita.rozetka.data.subcategory.ISubcategory;
import io.qameta.allure.Step;

import java.time.Duration;

import static com.codeborne.selenide.ClickOptions.usingJavaScript;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$x;
import static com.softserveinc.ita.rozetka.utils.WebElementUtil.getCollectionSize;

public class ComparisonListModal {
    private final String removeCategoryButtonXpath = "//button[contains(@class, 'remove')]";

    @Step("Comparison list modal: open comparison page for {subcategory}")
    public ComparisonPage openComparisonPage(ISubcategory subcategory) {
        $x(String.format("//rz-comparison-modal//a[contains(text(), '%s')]", subcategory.getSubcategoryNameUa())).click();
        return new ComparisonPage();
    }

    @Step("Comparison list modal: remove {category} category from comparison list")
    public ComparisonListModal remove(int category) {
        var comparisonIconCounter = $x("//rz-comparison//rz-icon-counter");
        var initialCounterText = comparisonIconCounter.text();
        var categoryRemoveButton = $x(String.format("(%s)[%d]", removeCategoryButtonXpath, category));
        categoryRemoveButton.click();
        comparisonIconCounter.shouldHave(text(initialCounterText), Duration.ofSeconds(2));
        try {
            categoryRemoveButton.shouldNotBe(visible, Duration.ofSeconds(2));
        } catch (AssertionError e) {
            categoryRemoveButton.click();
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
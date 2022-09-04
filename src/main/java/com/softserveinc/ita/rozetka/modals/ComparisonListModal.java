package com.softserveinc.ita.rozetka.modals;

import com.softserveinc.ita.rozetka.ComparisonPage;
import com.softserveinc.ita.rozetka.components.Header;
import com.softserveinc.ita.rozetka.data.subcategory.ISubcategory;
import io.qameta.allure.Step;

import java.time.Duration;
import java.util.NoSuchElementException;

import static com.codeborne.selenide.ClickOptions.usingJavaScript;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$x;
import static com.softserveinc.ita.rozetka.utils.WebElementUtil.isVisible;

public class ComparisonListModal {
    @Step("Comparison list modal: open comparison page for {subcategory}")
    public ComparisonPage openComparisonPage(ISubcategory subcategory) {
        $x(String.format("//rz-comparison-modal//a[contains(text(), '%s')]", subcategory.getSubcategoryNameUa())).click();
        return new ComparisonPage();
    }

    @Step("Comparison list modal: clear comparison list")
    public ComparisonListModal clear() {
        var removeFirstCategoryButtonXpath = "(//button[contains(@class, 'remove')])[1]";
        while (isVisible(removeFirstCategoryButtonXpath)) {
            try {
                $x(removeFirstCategoryButtonXpath).shouldBe(visible, Duration.ofSeconds(2)).click();
            } catch (NoSuchElementException ignore) {
            }
        }
        return this;
    }

    @Step("Comparison list modal: close comparison list modal")
    public Header close() {
        $x("//button[contains(@class, 'modal__close')]").click(usingJavaScript());
        return new Header();
    }
}
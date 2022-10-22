package com.softserveinc.ita.rozetka.modals;

import com.softserveinc.ita.rozetka.SearchResultsPage;
import com.softserveinc.ita.rozetka.SubcategoryPage;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$x;
import static com.softserveinc.ita.rozetka.utils.WebElementUtil.isVisible;

public class DrinkingAgeConfirmationModal {

    @Step("Drinking age confirmation modal: confirm user can legally consume alcoholic beverages")
    public SearchResultsPage confirm() {
        if (isVisible("//div[contains(@class, 'rz-content')]")) {
            $x("//a[contains(@class, 'exponea-close')]").click();
        }
        return new SubcategoryPage();
    }
}
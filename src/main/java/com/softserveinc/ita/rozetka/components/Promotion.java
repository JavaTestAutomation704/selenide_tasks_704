package com.softserveinc.ita.rozetka.components;

import com.softserveinc.ita.rozetka.PromotionPage;
import com.softserveinc.ita.rozetka.modals.DrinkingAgeConfirmationModal;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$x;
import static java.lang.String.format;

public class Promotion {
    private final String promotionXpath;

    public Promotion(int number) {
        this.promotionXpath = format("(//rz-promotion-tile)[%d]", number);
    }

    @Step("Promotion: open promotion page")
    public PromotionPage open() {
        $x(promotionXpath).click();
        new DrinkingAgeConfirmationModal().confirm();
        return new PromotionPage();
    }
}
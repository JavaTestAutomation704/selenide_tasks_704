package com.softserveinc.ita.rozetka.components;

import com.softserveinc.ita.rozetka.PromotionPage;
import com.softserveinc.ita.rozetka.utils.PromotionDates;
import com.softserveinc.ita.rozetka.modals.DrinkingAgeConfirmationModal;
import io.qameta.allure.Step;
import lombok.Getter;

import static com.codeborne.selenide.Selenide.$x;
import static java.lang.String.format;

public class Promotion {
    private final String promotionXpath;

    @Getter
    private final PromotionDates promotionDates;

    public Promotion(int number) {
        promotionXpath = format("(//rz-promotion-tile)[%d]", number);
        promotionDates = new PromotionDates(promotionXpath + "//time");
    }

    @Step("Promotion: open promotion page")
    public PromotionPage open() {
        $x(promotionXpath).click();
        new DrinkingAgeConfirmationModal().confirm();
        return new PromotionPage();
    }
}
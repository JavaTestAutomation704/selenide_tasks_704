package com.softserveinc.ita.rozetka.components;

import com.softserveinc.ita.rozetka.PromotionPage;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$x;

public class Promotion {
    private final String promotionXpath;

    public Promotion(int number) {
        this.promotionXpath = String.format("(//rz-promotion-tile)[%d]", number);
    }

    @Step("Promotion: open promotion page")
    public PromotionPage open() {
        $x(promotionXpath).click();
        return new PromotionPage();
    }
}
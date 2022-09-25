package com.softserveinc.ita.rozetka.components;

import com.softserveinc.ita.rozetka.PromotionPage;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$x;
import static com.softserveinc.ita.rozetka.utils.WebElementUtil.getText;
import static java.lang.String.format;

public class Promotion {
    private final String promotionXpath;

    public Promotion(int number) {
        this.promotionXpath = format("(//rz-promotion-tile)[%d]", number);
    }

    @Step("Promotion: open promotion page")
    public PromotionPage open() {
        $x(promotionXpath).click();
        return new PromotionPage();
    }

    public String getName() {
        return getText(promotionXpath + "//span[@class='promo-tile__heading']");
    }
}
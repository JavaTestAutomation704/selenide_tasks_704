package com.softserveinc.ita.rozetka.components.profile;

import com.softserveinc.ita.rozetka.models.CardData;
import io.qameta.allure.Step;
import lombok.RequiredArgsConstructor;

import static com.codeborne.selenide.Selenide.$x;
import static com.softserveinc.ita.rozetka.utils.WebElementUtil.*;

@RequiredArgsConstructor
public class CardDataSection {
    private final String cardDataSectionXpath;
    private final String inputCardNumberXpath = "//input[@id='cardNumber']";
    private final String inputMonthValidityXpath = "//input[@id='month']";
    private final String inputYearValidityXpath = "//input[@id='year']";
    private final String inputCardVerificationValueXpath = "//input[@id='CardVerificationValue']";

    @Step("Card data section: fill in card data {cardData}")
    public CardDataSection fillInCardData(CardData cardData) {
        $x(inputCardNumberXpath).val(cardData.getCardNumber());
        $x(inputMonthValidityXpath).val(cardData.getMonthValidity());
        $x(inputYearValidityXpath).val(cardData.getYearValidity());
        $x(inputCardVerificationValueXpath).val(cardData.getCardVerificationValue());
        return this;
    }

    public boolean isSaveButtonDisabled() {
        return hasAttribute(cardDataSectionXpath + "//button[@type='submit']", "disabled");
    }

    public boolean isCardNumberBorderColorCorrect(String colorRgb) {
        return isBorderColorCorrect(inputCardNumberXpath, colorRgb);
    }

    public boolean isMonthValidityBorderColorCorrect(String colorRgb) {
        return isBorderColorCorrect(inputMonthValidityXpath, colorRgb);
    }

    public boolean isYearValidityBorderColorCorrect(String colorRgb) {
        return isBorderColorCorrect(inputYearValidityXpath, colorRgb);
    }

    public boolean isCardVerificationValueBorderColorCorrect(String colorRgb) {
        return isBorderColorCorrect(inputCardVerificationValueXpath, colorRgb);
    }

    public boolean isOpened() {
        return isVisible("//rz-cabinet-wallet");
    }
}
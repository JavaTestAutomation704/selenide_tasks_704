package com.softserveinc.ita.rozetka;

import com.softserveinc.ita.rozetka.data.Color;
import com.softserveinc.ita.rozetka.models.CardData;
import com.softserveinc.ita.rozetka.utils.LogInViaFacebookTestRunner;
import org.assertj.core.api.SoftAssertions;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;


public class MyWalletTest extends LogInViaFacebookTestRunner {
    @Test
    public void verifyThatUserCanNotAddCardWithInvalidData() {
        var cardDataSection = homePage
                .getHeader()
                .openMainSidebar()
                .openProfilePage()
                .openCardDataSection();

        assertThat(cardDataSection.isOpened())
                .as("Card data section should be opened")
                .isTrue();

        var cardData = CardData
                .builder()
                .cardNumber("1234567")
                .monthValidity("1")
                .yearValidity("9")
                .CardVerificationValue("12")
                .build();

        cardDataSection.fillInCardData(cardData);

        var softly = new SoftAssertions();

        var redColor = Color.RED.getRgb();

        var isActualCardNumberBorderColorCorrect = cardDataSection.isCardNumberBorderColorCorrect(redColor);

        softly
                .assertThat(isActualCardNumberBorderColorCorrect)
                .as("Card Number border color should be red when entering card number invalid data")
                .isTrue();

        var isActualMonthValidityBorderColorCorrect = cardDataSection.isMonthValidityBorderColorCorrect(redColor);
        softly
                .assertThat(isActualMonthValidityBorderColorCorrect)
                .as("Month validity border color should be red when entering month validity invalid data")
                .isTrue();

        var isActualYearValidityBorderColorCorrect = cardDataSection.isYearValidityBorderColorCorrect(redColor);
        softly
                .assertThat(isActualYearValidityBorderColorCorrect)
                .as("Year validity border color should be red when entering year validity invalid data")
                .isTrue();

        var isActualCardVerificationValueBorderColorCorrect = cardDataSection.isCardVerificationValueBorderColorCorrect(redColor);
        softly
                .assertThat(isActualCardVerificationValueBorderColorCorrect)
                .as("Card Verification Value border color should be red when entering CVV invalid date")
                .isTrue();

        softly.assertThat(cardDataSection.isSaveButtonDisabled())
                .as("Save button should be disabled")
                .isTrue();

        softly.assertAll();
    }
}
package com.softserveinc.ita.rozetka;

import com.softserveinc.ita.rozetka.utils.TestRunner;
import org.assertj.core.api.SoftAssertions;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class PromotionsTest extends TestRunner {

    @Test
    public void verifyPromotionTermsCorrespondence() {

        var promotionsPage = homePage.openPromotionsPage();

        assertThat(promotionsPage.isOpen())
                .as("Promotions page should be open")
                .isTrue();

        int promotionNumber = 2;

        assertThat(promotionsPage.getPromotionsQuantity())
                .as("The promotions quantity should be sufficient on the promotions page")
                .isGreaterThanOrEqualTo(promotionNumber);

        var promotionPage = promotionsPage
                .getPromotion(promotionNumber)
                .open();

        int productNumber = 1;

        assertThat(promotionPage.getProductsQuantity())
                .as("The products quantity should be sufficient on the promotion page")
                .isGreaterThanOrEqualTo(productNumber);

        var actionTermsModal = promotionPage
                .getProduct(productNumber)
                .open()
                .openActionTermsModal();

        assertThat(actionTermsModal.isOpen())
                .as("Action terms modal should be open")
                .isTrue();

        var modalTitle = actionTermsModal.getTitle();
        var promotionPeriodOnModal = actionTermsModal.getPromotionPeriod();

        promotionPage = actionTermsModal.openPromotionPage();

        assertThat(promotionPage.isOpen())
                .as("Promotion page should be open")
                .isTrue();

        var softAssertions = new SoftAssertions();

        var pageTitle = promotionPage.getTitle();
        var promotionPeriodOnPage = promotionPage.getPromotionPeriod();

        softAssertions
                .assertThat(modalTitle)
                .as("Promotion name on the action terms modal should be the same as on the promotion page")
                .isEqualTo(pageTitle);

        softAssertions
                .assertThat(promotionPeriodOnModal)
                .as("Promotion period on the action terms modal should be the same as on the promotion page")
                .isEqualTo(promotionPeriodOnPage);

        softAssertions.assertAll();
    }
}
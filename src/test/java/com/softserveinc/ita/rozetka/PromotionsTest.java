package com.softserveinc.ita.rozetka;

import com.softserveinc.ita.rozetka.utils.TestRunner;
import org.assertj.core.api.SoftAssertions;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class PromotionsTest extends TestRunner {

    @Test
    public void verifyThatPromotionTermsAreTheSameOnDifferentPages() {

        var promotionsPage = homePage.openPromotionsPage();

        assertThat(promotionsPage.isOpen())
                .as("Promotions page should be open")
                .isTrue();

        int promotionNumber = 1;

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

        var productPage = promotionPage
                .getProduct(productNumber)
                .open();

        assertThat(productPage.isOpenPromotionTermsModalButtonVisible())
                .as("There should be open promotion terms modal button on the product page")
                .isTrue();

        var promotionTermsModal = productPage.openPromotionTermsModal();

        assertThat(promotionTermsModal.isOpen())
                .as("Promotion terms modal should be open")
                .isTrue();

        var modalTitle = promotionTermsModal.getTitle();
        var promotionPeriodOnModal = promotionTermsModal.getPromotionPeriod();

        promotionPage = promotionTermsModal.openPromotionPage();

        assertThat(promotionPage.isOpen())
                .as("Promotion page should be open")
                .isTrue();

        var softAssertions = new SoftAssertions();

        var pageTitle = promotionPage.getTitle();
        var promotionPeriodOnPage = promotionPage.getPromotionPeriod();

        softAssertions
                .assertThat(modalTitle)
                .as("Promotion name on the promotion terms modal should be the same as on the promotion page")
                .isEqualTo(pageTitle);

        softAssertions
                .assertThat(promotionPeriodOnModal)
                .as("Promotion period on the promotion terms modal should be the same as on the promotion page")
                .isEqualTo(promotionPeriodOnPage);

        softAssertions.assertAll();
    }
}
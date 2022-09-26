package com.softserveinc.ita.rozetka;

import com.softserveinc.ita.rozetka.data.PromotionFilter;
import com.softserveinc.ita.rozetka.utils.BaseTestRunner;
import org.assertj.core.api.SoftAssertions;
import org.testng.annotations.Test;

import static com.softserveinc.ita.rozetka.data.Language.UA;
import static org.assertj.core.api.Assertions.assertThat;

public class PromotionsTest extends BaseTestRunner {

    @Test
    public void verifyThatPromotionTermsAreTheSameOnDifferentPages() {
        var header = homePage.getHeader();
        header.changeLanguage(UA);
        var isUaLanguageSelected = header.isLanguageSelected(UA);

        assertThat(isUaLanguageSelected)
                .as("Localization should be switched to UA")
                .isTrue();

        var promotionsPage = homePage.openPromotionsPage();

        assertThat(promotionsPage.isOpened())
                .as("Promotions page should be opened")
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
                .as("There should be opened promotion terms modal button on the product page")
                .isTrue();

        var promotionTermsModal = productPage.openPromotionTermsModal();

        assertThat(promotionTermsModal.isOpened())
                .as("Promotion terms modal should be opened")
                .isTrue();

        var modalTitle = promotionTermsModal.getTitle();
        var promotionPeriodOnModal = promotionTermsModal.getPromotionPeriod();

        promotionPage = promotionTermsModal.openPromotionPage();

        assertThat(promotionPage.isOpened())
                .as("Promotion page should be opened")
                .isTrue();

        var softly = new SoftAssertions();

        var pageTitle = promotionPage.getTitle();
        var promotionPeriodOnPage = promotionPage.getPromotionPeriod();

        softly
                .assertThat(pageTitle)
                .as("Promotion name on the promotion page should be the same as on the promotion terms modal")
                .isEqualTo(modalTitle);

        // TODO: This test may be failed as promotion period on the promotion terms modal and on the promotion page
        //  may be different
        softly
                .assertThat(promotionPeriodOnPage)
                .as("Promotion period on the promotion page should be the same as on the promotion terms modal")
                .contains(promotionPeriodOnModal);

        softly.assertAll();
    }

    @Test
    public void verifyFilterWithGifts() {
        var header = homePage.getHeader();
        header.changeLanguage(UA);
        var isUaLanguageSelected = header.isLanguageSelected(UA);

        assertThat(isUaLanguageSelected)
                .as("Localization should be switched to UA")
                .isTrue();

        var promotionsPage = homePage.openPromotionsPage();
        assertThat(promotionsPage.isOpened())
                .as("Promotions page should be opened")
                .isTrue();

        promotionsPage.filter(PromotionFilter.WITH_GIFTS);

        assertThat(promotionsPage.getPromotionsQuantity())
                .as("The products quantity should be sufficient on the promotion page")
                .isGreaterThanOrEqualTo(10);

        var softly = new SoftAssertions();
        for (int number = 1; number < 10; number++) {
            var promotionName = promotionsPage
                    .getPromotion(number)
                    .getName();

            softly.assertThat(promotionName)
                    .as("Promotion page should contain 'подарунок' word")
                    .contains("подарунок");
        }
        softly.assertAll();
    }
}
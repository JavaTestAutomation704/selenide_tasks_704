package com.softserveinc.ita.rozetka;

import com.softserveinc.ita.rozetka.data.Language;
import com.softserveinc.ita.rozetka.utils.LogInViaFacebookTestRunner;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class WriteReviewTest extends LogInViaFacebookTestRunner {
    @Test
    public void verifyImpossibilityWriteReviewWithInvalidData() {
        var isLanguageUa = homePage
                .getHeader()
                .isLanguageSelected(Language.UA);

        assertThat(isLanguageUa)
                .as("Language should be ukrainian")
                .isTrue();

        var searchResultsPage = homePage
                .getHeader()
                .search("macbook");

        assertThat(searchResultsPage.getProductsQuantity())
                .as("Product quantity should be sufficient")
                .isGreaterThanOrEqualTo(1);

        var writeReviewModal = searchResultsPage
                .getProduct(1)
                .open()
                .openReviewPage()
                .writeReview();

        assertThat(writeReviewModal.isModalOpen())
                .as("Write review modal should be opened")
                .isTrue();

        String invalidData = " ";

        writeReviewModal = writeReviewModal
                .writeAdvantages(invalidData)
                .writeDisadvantages(invalidData)
                .writeComment(invalidData)
                .startSubmitting();

        assertThat(writeReviewModal.getErrorsQuantity())
                .as("Errors quantity should be sufficient")
                .isGreaterThanOrEqualTo(2);

        var errorsList = writeReviewModal.getErrors();

        assertThat(errorsList)
                .as("Errors list should contains this error")
                .contains("Необхідно виставити оцінку");

        assertThat(errorsList)
                .as("Errors list should contains this error")
                .contains("Поле обов'язкове до заповнення");
    }
}

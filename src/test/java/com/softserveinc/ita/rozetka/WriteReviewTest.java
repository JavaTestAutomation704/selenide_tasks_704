package com.softserveinc.ita.rozetka;

import com.softserveinc.ita.rozetka.data.Language;
import com.softserveinc.ita.rozetka.utils.LogInViaFacebookTestRunner;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class WriteReviewTest extends LogInViaFacebookTestRunner {
    @Test
    public void verifyImpossibilityToWriteReviewWithInvalidData() {
        var header = homePage.getHeader();
        var isLanguageUa = header.isLanguageSelected(Language.UA);

        assertThat(isLanguageUa)
                .as("Language should be ukrainian")
                .isTrue();

        var searchResultsPage = header.search("macbook");

        assertThat(searchResultsPage.getProductsQuantity())
                .as("Product quantity should be sufficient")
                .isGreaterThanOrEqualTo(1);

        var writeReviewModal = searchResultsPage
                .getProduct(1)
                .open()
                .openReviewPage()
                .startWritingReviewWhenUserAuthorized();

        assertThat(writeReviewModal.isOpen())
                .as("Write review modal should be opened")
                .isTrue();

        var invalidString = " ";

        writeReviewModal = writeReviewModal
                .addPros(invalidString)
                .addCons(invalidString)
                .addComment(invalidString)
                .submit();

        var errorsList = writeReviewModal.getErrors();

        assertThat(errorsList)
                .size()
                .as("Errors quantity should be sufficient")
                .isGreaterThanOrEqualTo(2);

        assertThat(errorsList)
                .as("Errors list should contains this error")
                .contains("Необхідно виставити оцінку");

        assertThat(errorsList)
                .as("Errors list should contains this error")
                .contains("Мінімальна кількість символів: 1/3");
    }
}

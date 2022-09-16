package com.softserveinc.ita.rozetka;

import com.softserveinc.ita.rozetka.data.ProductQuestionsSort;
import com.softserveinc.ita.rozetka.utils.TestRunner;
import org.assertj.core.api.SoftAssertions;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ProductQuestionsTest extends TestRunner {
    @Test
    public void verifySortProductQuestionsFunctionality() {
        var searchResultsPage = homePage
                .getHeader()
                .search("Телевізор Samsung");

        assertThat(searchResultsPage.getProductsQuantity())
                .as("Product quantity should be sufficient")
                .isGreaterThanOrEqualTo(1);

        var productQuestionsPage = searchResultsPage
                .getProduct(1)
                .open()
                .openProductQuestionsPage();

        int productQuestionsQuantity = productQuestionsPage.getProductQuestionsQuantity();

        assertThat(productQuestionsQuantity)
                .as("Product questions quantity should be sufficient")
                .isGreaterThanOrEqualTo(1);

        productQuestionsPage = productQuestionsPage.sort(ProductQuestionsSort.WITH_PHOTO);

        assertThat(productQuestionsQuantity)
                .as("Product questions quantity should be sufficient")
                .isGreaterThanOrEqualTo(1);

        var softly = new SoftAssertions();
        int productQuestionsWithPhotosQuantity = productQuestionsPage.getProductQuestionsWithPhotosQuantity();

        for (int number = 1; number < productQuestionsWithPhotosQuantity; number++) {
            var hasPhoto = productQuestionsPage
                    .getProductQuestionItem(number)
                    .hasPhoto();

            softly.assertThat(hasPhoto)
                    .as(number + " Product question item should have a photo")
                    .isTrue();
        }

        for (int number = productQuestionsWithPhotosQuantity; number < productQuestionsQuantity; number++) {
            var hasPhoto = productQuestionsPage
                    .getProductQuestionItem(number)
                    .hasPhoto();

            softly.assertThat(hasPhoto)
                    .as(number + " Product question item should not have a photo")
                    .isFalse();
        }
        softly.assertAll();
    }

    @Test
    public void verifySortByDateProductQuestionsFunctionality() {
        var searchResultsPage = homePage
                .getHeader()
                .search("iphone");

        assertThat(searchResultsPage.getProductsQuantity())
                .as("Product quantity should be sufficient")
                .isGreaterThanOrEqualTo(1);

        var productQuestionsPage = searchResultsPage
                .getProduct(1)
                .open()
                .openProductQuestionsPage();

        assertThat(productQuestionsPage.getProductQuestionsQuantity())
                .as("Product questions quantity should be sufficient")
                .isGreaterThanOrEqualTo(30);

        productQuestionsPage = productQuestionsPage.sort(ProductQuestionsSort.BY_DATE);

        assertThat(productQuestionsPage.getProductQuestionsQuantity())
                .as("Product questions quantity should be sufficient")
                .isGreaterThanOrEqualTo(25);

        var softly = new SoftAssertions();

        for (int number = 1; number < 25; number += 4) {
            var firstDate = productQuestionsPage
                    .getProductQuestionItem(number)
                    .getDate();

            var secondDate = productQuestionsPage
                    .getProductQuestionItem(number + 1)
                    .getDate();

            softly.assertThat(firstDate)
                    .as("second date should be occurs before or equal to first date")
                    .isAfterOrEqualTo(secondDate);
        }
        softly.assertAll();
    }
}
package com.softserveinc.ita.rozetka;

import com.softserveinc.ita.rozetka.data.ReviewsSort;
import com.softserveinc.ita.rozetka.utils.BaseTestRunner;
import org.assertj.core.api.SoftAssertions;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;


public class ReviewTest extends BaseTestRunner {
    @Test
    public void verifyFilterReviewsByRating() {
        var searchResultsPage = homePage
                .getHeader()
                .search("samsung a52");

        assertThat(searchResultsPage.getProductsQuantity())
                .as("Product quantity should be sufficient")
                .isGreaterThanOrEqualTo(1);

        var reviewPage = searchResultsPage
                .getProduct(1)
                .open()
                .openReviewPage();

        int initialQuantity = reviewPage.getReviewsQuantity();
        assertThat(initialQuantity)
                .as("Reviews quantity should be sufficient")
                .isGreaterThanOrEqualTo(30);

        int expectedRating = 5;
        reviewPage = reviewPage.filterByRating(expectedRating);

        assertThat(reviewPage.getReviewsQuantity())
                .as("Reviews quantity should be sufficient")
                .isLessThanOrEqualTo(initialQuantity)
                .isGreaterThanOrEqualTo(20);

        var softly = new SoftAssertions();
        for (int number = 2; number < 20; number += 3) {
            int actualRating = reviewPage
                    .getReviewItem(number)
                    .getRating();

            softly.assertThat(actualRating)
                    .as(number + " review should have expected rating")
                    .isEqualTo(expectedRating);
        }
        softly.assertAll();
    }

    @Test
    public void verifySortByPhotosReviewsFunctionality() {
        var searchResultsPage = homePage
                .getHeader()
                .search("телефони");

        assertThat(searchResultsPage.getProductsQuantity())
                .as("Product quantity should be sufficient")
                .isGreaterThanOrEqualTo(1);

        var reviewPage = searchResultsPage
                .getProduct(1)
                .open()
                .openReviewPage();

        assertThat(reviewPage.getReviewsQuantity())
                .as("Reviews quantity should be sufficient")
                .isGreaterThanOrEqualTo(30);

        reviewPage = reviewPage.sort(ReviewsSort.WITH_PHOTO);

        assertThat(reviewPage.getReviewsQuantity())
                .as("Reviews quantity should be sufficient")
                .isGreaterThanOrEqualTo(25);

        var softly = new SoftAssertions();
        for (int number = 1; number < 5; number++) {
            var hasPhoto = reviewPage
                    .getReviewItem(number)
                    .hasPhoto();

            softly.assertThat(hasPhoto)
                    .as(number + " review should have a photo")
                    .isTrue();
        }

        softly.assertAll();
    }

    @Test
    public void verifySortByDateReviewsFunctionality() {
        var searchResultsPage = homePage
                .getHeader()
                .search("macbook");

        assertThat(searchResultsPage.getProductsQuantity())
                .as("Product quantity should be sufficient")
                .isGreaterThanOrEqualTo(1);

        var reviewPage = searchResultsPage
                .getProduct(1)
                .open()
                .openReviewPage();

        assertThat(reviewPage.getReviewsQuantity())
                .as("Reviews quantity should be sufficient")
                .isGreaterThanOrEqualTo(30);

        reviewPage = reviewPage.sort(ReviewsSort.BY_DATE);

        assertThat(reviewPage.getReviewsQuantity())
                .as("Reviews quantity should be sufficient")
                .isGreaterThanOrEqualTo(25);

        var softly = new SoftAssertions();
        for (int number = 1; number < 25; number += 2) {
            var firstDate = reviewPage
                    .getReviewItem(number)
                    .getDate();

            var secondDate = reviewPage
                    .getReviewItem(number + 1)
                    .getDate();

            softly.assertThat(firstDate)
                    .as("second date should be occurs before or equal to first date")
                    .isAfterOrEqualTo(secondDate);
        }

        softly.assertAll();
    }

    @Test
    public void verifyThatUnregisteredUserCanNotWriteReview() {
        var searchResultsPage = homePage
                .getHeader()
                .search("телефон");

        assertThat(searchResultsPage.getProductsQuantity())
                .as("Product quantity should be sufficient")
                .isGreaterThanOrEqualTo(1);

        var reviewPage = searchResultsPage
                .getProduct(1)
                .open()
                .openReviewPage();

        reviewPage = reviewPage.startWritingReview();
        assertThat(reviewPage.isLoginModalOpen())
                .as("Login modal should be open")
                .isTrue();
    }
}

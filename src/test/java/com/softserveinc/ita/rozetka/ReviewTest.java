package com.softserveinc.ita.rozetka;

import com.softserveinc.ita.rozetka.data.ReviewsSort;
import com.softserveinc.ita.rozetka.utils.TestRunner;
import org.assertj.core.api.SoftAssertions;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;


public class ReviewTest extends TestRunner {
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

        var softAssertions = new SoftAssertions();
        for (int number = 2; number < 20; number += 3) {
            int actualRating = reviewPage
                    .getReviewItem(number)
                    .getRating();

            softAssertions.assertThat(actualRating)
                    .as(number + " review should have expected rating")
                    .isEqualTo(expectedRating);
        }
        softAssertions.assertAll();
    }

    @Test
    public void verifySortReviewsFunctionality() {
        homePage
                .getHeader()
                .search("Телевізор Samsung UE32T5300AUXUA");

        var productPage = new ProductPage();
        assertThat(productPage.isOpened())
                .as("Product page should be open")
                .isTrue();

        var reviewPage = productPage.openReviewPage();

        assertThat(reviewPage.getReviewsQuantity())
                .as("Reviews quantity should be sufficient")
                .isGreaterThanOrEqualTo(30);

        reviewPage = reviewPage.sort(ReviewsSort.WITH_PHOTO);

        assertThat(reviewPage.getReviewsQuantity())
                .as("Reviews quantity should be sufficient")
                .isGreaterThanOrEqualTo(25);

        var softAssertions = new SoftAssertions();
        for (int number = 1; number < 18; number++) {
            var hasPhoto = reviewPage
                    .getReviewItem(number)
                    .hasPhoto();

            softAssertions.assertThat(hasPhoto)
                    .as(number + " review should have a photo")
                    .isTrue();
        }

        for (int number = 18; number < 25; number++) {
            var hasPhoto = reviewPage
                    .getReviewItem(number)
                    .hasPhoto();

            softAssertions.assertThat(hasPhoto)
                    .as(number + " review should not have a photo")
                    .isFalse();
        }

        softAssertions.assertAll();
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

        reviewPage = reviewPage.tryToWriteReview();
        assertThat(reviewPage.isLoginModalOpen())
                .as("Login modal should be open")
                .isTrue();
    }
}

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
                    .as(number + " review has not got this rating")
                    .isEqualTo(expectedRating);
        }
        softAssertions.assertAll();
    }

    @Test
    public void verifySortReviewsFunctionality() {
        var searchResultsPage = homePage
                .getHeader()
                .search("телевізор samsung");

        assertThat(searchResultsPage.getProductsQuantity())
                .as("Product quantity should be sufficient")
                .isGreaterThanOrEqualTo(3);

        var reviewPage = searchResultsPage
                .getProduct(2)
                .open()
                .openReviewPage();

        int startQuantity = reviewPage.getReviewsQuantity();
        assertThat(startQuantity)
                .as("Reviews quantity should be sufficient")
                .isGreaterThanOrEqualTo(30);

        reviewPage = reviewPage.sort(ReviewsSort.WITH_PHOTO);

        assertThat(reviewPage.getReviewsQuantity())
                .as("Reviews quantity should be sufficient")
                .isGreaterThanOrEqualTo(25);

        var softAssertions = new SoftAssertions();
        for (int number = 1; number < 25; number += 3) {
            var hasPhoto = reviewPage
                    .getReviewItem(number)
                    .hasPhoto();

            // TODO: This test may be failed as reviews has not have photo
            softAssertions.assertThat(hasPhoto)
                    .as(number + " review has not got photo")
                    .isTrue();
        }
        softAssertions.assertAll();
    }
}

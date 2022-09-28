package com.softserveinc.ita.rozetka;

import com.softserveinc.ita.rozetka.data.ProductQuestionsSort;
import com.softserveinc.ita.rozetka.utils.BaseTestRunner;
import org.assertj.core.api.SoftAssertions;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ProductQuestionsTest extends BaseTestRunner {

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

        int productQuestionsQuantity = productQuestionsPage.getProductQuestionsQuantity();

        assertThat(productQuestionsQuantity)
                .as("Product questions quantity should be sufficient")
                .isGreaterThanOrEqualTo(2);

        productQuestionsPage = productQuestionsPage.sort(ProductQuestionsSort.BY_DATE);

        assertThat(productQuestionsQuantity)
                .as("Product questions quantity should be sufficient")
                .isGreaterThanOrEqualTo(2);

        var softly = new SoftAssertions();

        for (int number = 1; number < productQuestionsQuantity; number += 2) {
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
    
 @Test
    public void verifySortProductQuestionsByVoteFunctionality() {
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

        productQuestionsPage = productQuestionsPage.sort(ProductQuestionsSort.BY_VOTE);

        assertThat(productQuestionsQuantity)
                .as("Product questions quantity should be sufficient")
                .isGreaterThanOrEqualTo(1);

        var softly = new SoftAssertions();
        int step = 4;

        for (int i = step + 1; i < productQuestionsQuantity; i += step) {
            softly
                    .assertThat(productQuestionsPage
                            .getProductQuestionItem(i)
                            .getLikesAndDislikesDifference())
                    .as(i + " product question vote should be less than " + (i - step))
                    .isLessThanOrEqualTo(productQuestionsPage
                            .getProductQuestionItem(i - step)
                            .getLikesAndDislikesDifference());
        }
        softly.assertAll();
    }
    
     @Test
    public void verifyThatAdditionalProductPriceIsLessThanItsUsualPrice() {
        var searchResultsPage = homePage
                .getHeader()
                .search("samsung");

        assertThat(searchResultsPage.getProductsQuantity())
                .as("Product quantity should be sufficient")
                .isGreaterThanOrEqualTo(1);

        var cheaperTogetherSection = searchResultsPage
                .getProduct(1)
                .open()
                .getCheaperTogetherSection();

        var additionalProductActualPrice = cheaperTogetherSection.getAdditionalProductActualPrice();
        var additionalProductOldPrice = cheaperTogetherSection.getAdditionalProductOldPrice();

        assertThat(additionalProductActualPrice)
                .as("Additional product should be cheaper than usual")
                .isLessThan(additionalProductOldPrice);

        var mainProductPrice = cheaperTogetherSection.getMainProductPrice();
        assertThat(additionalProductActualPrice + mainProductPrice)
                .as("Total price should be correct")
                .isEqualTo(cheaperTogetherSection.getTotalSum());

        var additionalProductPage = cheaperTogetherSection.openAdditionalProductPage();
        assertThat(additionalProductPage.isOpened())
                .as("Additional product page should be opened")
                .isTrue();

        assertThat(additionalProductPage.getPrice())
                .as("Price should be the same")
                .isEqualTo(additionalProductOldPrice);
    }
}
package com.softserveinc.ita.rozetka;

import com.softserveinc.ita.rozetka.data.ProductQuestionsSort;
import com.softserveinc.ita.rozetka.utils.TestRunner;
import org.assertj.core.api.SoftAssertions;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ProductTest extends TestRunner {
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

        var softAssertions = new SoftAssertions();
        int productQuestionsWithPhotosQuantity = productQuestionsPage.getProductQuestionsWithPhotosQuantity();

        for (int number = 1; number < productQuestionsWithPhotosQuantity; number++) {
            var hasPhoto = productQuestionsPage
                    .getProductQuestionItem(number)
                    .hasPhoto();

            softAssertions.assertThat(hasPhoto)
                    .as(number + " Product question item should have a photo")
                    .isTrue();
        }

        for (int number = productQuestionsWithPhotosQuantity; number < productQuestionsQuantity; number++) {
            var hasPhoto = productQuestionsPage
                    .getProductQuestionItem(number)
                    .hasPhoto();

            softAssertions.assertThat(hasPhoto)
                    .as(number + " Product question item should not have a photo")
                    .isFalse();
        }
        softAssertions.assertAll();
    }

    @Test
    public void verifyThatTogetherIsCheaper() {
        var searchResultsPage = homePage
                .getHeader()
                .search("samsung");

        assertThat(searchResultsPage.getProductsQuantity())
                .as("Product quantity should be sufficient")
                .isGreaterThanOrEqualTo(1);

        var cheaperTogetherSection = searchResultsPage
                .getProduct(1)
                .open()
                .goToCheaperTogetherSection();

        var additionProductActualPrice = cheaperTogetherSection.getAdditionProductActualPrice();
        var additionProductOldPrice = cheaperTogetherSection.getAdditionProductOldPrice();

        assertThat(additionProductActualPrice)
                .as("Addition product should be cheaper as usual")
                .isLessThan(additionProductOldPrice);

        var mainProductPrice = cheaperTogetherSection.getMainProductPrice();
        assertThat(additionProductActualPrice + mainProductPrice)
                .as("Total price should be correct")
                .isEqualTo(cheaperTogetherSection.totalPrice());

        var additionProductPage = cheaperTogetherSection.openAdditionProductPage();
        assertThat(additionProductPage.isOpened())
                .as("Addition product page should be opened")
                .isTrue();

        assertThat(additionProductPage.getPrice())
                .as("Price should be the same")
                .isEqualTo(additionProductOldPrice);
    }
}
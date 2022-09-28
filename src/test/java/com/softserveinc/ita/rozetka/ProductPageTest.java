package com.softserveinc.ita.rozetka;

import com.softserveinc.ita.rozetka.utils.BaseTestRunner;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ProductPageTest extends BaseTestRunner {
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

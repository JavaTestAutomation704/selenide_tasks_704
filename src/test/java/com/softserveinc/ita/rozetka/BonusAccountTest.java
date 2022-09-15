package com.softserveinc.ita.rozetka;

import com.softserveinc.ita.rozetka.data.Category;
import com.softserveinc.ita.rozetka.utils.TestRunner;
import org.assertj.core.api.SoftAssertions;
import org.testng.annotations.Test;

public class BonusAccountTest extends TestRunner {

    @Test
    public void verifyBonusesOnProduct() {
        var softly = new SoftAssertions();
        var bonusAccountPage = homePage.openBonusAccountPage();

        softly.assertThat(bonusAccountPage.isBannerVisible())
                .as("Banner should be visible")
                .isTrue();

        var productWithBonusesCategoryPage = bonusAccountPage.openProductWithBonusesCategoryPage();

        softly.assertThat(productWithBonusesCategoryPage.isTitleContainsBonus())
                .as("Title incorrect")
                .isTrue();
        var productWithBonusesPage = productWithBonusesCategoryPage.openProductWithBonusesPage(Category.LAPTOPS_AND_COMPUTERS);

        int productsQuantity = productWithBonusesPage.getProductsQuantity();
        int productsQuantityToCheck = 30;

        softly.assertThat(productsQuantity)
                .as("Products quantity should be sufficient")
                .isGreaterThanOrEqualTo(productsQuantityToCheck);

        for (int i = 1; i <= productsQuantity; i = i + 10) {
            var productPage = productWithBonusesPage
                    .getProduct(i)
                    .open();

            //TODO: This test may be failed as unavailable products might be among the results
            softly.assertThat(productPage.isBonusIconVisible())
                    .as("Bonus icon should be visible")
                    .isTrue();

            productPage.back();
        }
        softly.assertAll();
    }
}

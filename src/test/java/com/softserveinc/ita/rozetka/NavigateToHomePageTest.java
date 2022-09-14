package com.softserveinc.ita.rozetka;

import com.softserveinc.ita.rozetka.data.Category;
import com.softserveinc.ita.rozetka.data.subcategory.LaptopsAndComputersSubcategory;
import com.softserveinc.ita.rozetka.utils.TestRunner;
import org.assertj.core.api.SoftAssertions;
import org.testng.annotations.Test;

public class NavigateToHomePageTest extends TestRunner {

    @Test
    public void verifyOpeningHomePageViaLogo() {
        var header = homePage.getHeader();

        var smallCart = homePage.getSmallCart();

        if (header.isShoppingCartCounterVisible()) {
            var shoppingCartModal = header
                    .openShoppingCartModal()
                    .clear();
            if (shoppingCartModal.isCloseButtonVisible()) {
                shoppingCartModal.close();
            } else {
                homePage.back();
            }
        }

        var softly = new SoftAssertions();
        softly.assertThat(header.isShoppingCartCounterVisible())
                .as("Cart should be empty")
                .isFalse();
        softly.assertThat(smallCart.isOpened())
                .as("Small cart should not be opened")
                .isFalse();

        var subcategoryPage = homePage
                .openCategoryPage(Category.LAPTOPS_AND_COMPUTERS)
                .openSubcategoryPage(LaptopsAndComputersSubcategory.NOTEBOOKS);

        softly.assertThat(subcategoryPage.getProductsQuantity())
                .as("Products quantity should be sufficient")
                .isGreaterThanOrEqualTo(1);

        var product = subcategoryPage.getProduct(1);
        product.addToShoppingCart();

        softly.assertThat(product.isInShoppingCart())
                .as("First product should be added to shopping cart")
                .isTrue();

        header.openHomePageViaLogo();

        softly.assertThat(smallCart.isOpened())
                .as("Small cart should be opened")
                .isTrue();
        softly.assertThat(homePage.isMainCategoriesSectionVisible())
                .as("Main categories should be visible")
                .isTrue();

        homePage.openCategoryPage(Category.LAPTOPS_AND_COMPUTERS);
        header.openHomePageViaLogo();

        softly.assertThat(smallCart.isOpened())
                .as("Small cart should be opened")
                .isTrue();
        softly.assertThat(homePage.isMainCategoriesSectionVisible())
                .as("Main categories should be visible")
                .isTrue();

        header
                .openMainSidebar()
                .openHomePageViaLogo();

        softly.assertThat(smallCart.isOpened())
                .as("Small cart should be opened")
                .isTrue();
        softly.assertAll();
    }
}